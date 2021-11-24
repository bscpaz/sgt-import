package br.com.bscpaz.sgt.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.bscpaz.sgt.entities.pje.AssuntoTrf;
import br.com.bscpaz.sgt.entities.sgt.Assunto;
import br.com.bscpaz.sgt.entities.sgt.AssuntoAvaliado;
import br.com.bscpaz.sgt.repositories.pje.AssuntoTrfRepository;
import br.com.bscpaz.sgt.repositories.sgt.AssuntoAvaliadoRepository;
import br.com.bscpaz.sgt.repositories.sgt.AssuntoRepository;
import br.com.bscpaz.sgt.utils.ExcelAvaliadoStructure;
import br.com.bscpaz.sgt.utils.ExcelStructure;
import br.com.bscpaz.sgt.utils.FileUtil;
import br.com.bscpaz.sgt.vos.AssuntoDecorator;
import br.com.bscpaz.sgt.vos.AssuntoFields;
import br.com.bscpaz.sgt.vos.HierarchicalDescription;
import br.com.bscpaz.sgt.vos.ImportConfig;

@Service
@EnableTransactionManagement
public class AssuntoService {

	private List<AssuntoDecorator> newAssuntosToImport = new ArrayList<>();

	@Autowired
	private AssuntoRepository assuntoRepository;

	@Autowired
	private AssuntoAvaliadoRepository assuntoAvaliadoRepository;

	@Autowired
	private AssuntoTrfRepository assuntoTrfRepository;

	private boolean isAllAssuntosIdentified = false;

	public void doImport(ImportConfig importConfig) {
		if (!importConfig.isSkipXlsInputFile()) {
			importInputExcelIntoTempDatabase(importConfig);
		}
		generateImportSqlFile(importConfig);
	}

	@Transactional("sgtTransactionManager")
	private void importInputExcelIntoTempDatabase(ImportConfig importConfig) {
		String xlsFile = importConfig.getXlsInputFile();
		System.out.println(String.format("\n\n\nImporting data from '%s' into sgt database...", xlsFile));

		this.assuntoRepository.deleteAll();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream fis = classloader.getResourceAsStream(xlsFile);
		XSSFWorkbook wb = null; //HSSFWorkbook wb = null;
		int rowCount = 0;
		boolean isChildNode = false;

		try {
			wb = new XSSFWorkbook(fis); //wb = new HSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); //HSSFSheet sheet =  wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			HierarchicalDescription hierarchicalDescription = new HierarchicalDescription();
			int assuntoArrayIndex = 0;

			while (rowIterator.hasNext()) { 
				Row row = rowIterator.next(); 
				rowCount++;

				if (isRowWithNoValidContent(rowCount, row)) {
					continue;
				}
				
				Assunto assunto = new Assunto();
				Iterator<Cell> cellIterator = row.cellIterator(); 
	
				while (cellIterator.hasNext()) { 
					String value = null;
					Cell cell = cellIterator.next(); 
					int excelColumnIndex = cell.getColumnIndex();

					switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							break;
						case NUMERIC:
							value = getCodigoAsString(cell.getNumericCellValue());
							break;
						default:
							break;
					}

					switch(excelColumnIndex) {
						case ExcelStructure.DESCRICAO_1_COLUMN:
						case ExcelStructure.DESCRICAO_2_COLUMN:
						case ExcelStructure.DESCRICAO_3_COLUMN:
						case ExcelStructure.DESCRICAO_4_COLUMN:
						case ExcelStructure.DESCRICAO_5_COLUMN:
							if (assunto.getAssunto() == null && value != null && !value.trim().equals("")) {
								//'Descricao' is spread amoung those all columns.
								assunto.setAssunto(value);
								assuntoArrayIndex = excelColumnIndex;
							}
							break;

						case ExcelStructure.CODIGO_COLUMN:
							assunto.setCodigo(value);
							break;
						
						case ExcelStructure.CODIGO_SUP_COLUMN:
							if (value != null) {
								assunto.setCodigoSuperior(value);
								isChildNode = true;
							} else {
								isChildNode = false;
							}
							break;

						case ExcelStructure.NORMA_COLUMN:
							assunto.setNorma(value);
							break;

						case ExcelStructure.ARTIGO_COLUMN:
							assunto.setNorma(value);
							break;

						case ExcelStructure.ALTERACOES_COLUMN:
							assunto.setAlteracoes(value);
							break;

						case ExcelStructure.GLOSSARIO_COLUMN:
							assunto.setGlossario(value);
							break;
					}
				}

				if (isChildNode) {
					assuntoArrayIndex++;
				}
				hierarchicalDescription.setValue(assuntoArrayIndex, assunto.getAssunto(), assunto.getCodigo());
				assunto.setAssuntoCompleto(hierarchicalDescription.getValue());
				this.assuntoRepository.save(assunto);
			}
		} catch (Exception e) {
			System.out.println(String.format("\nERROR at line: %d", rowCount));
			e.printStackTrace();
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void generateImportSqlFile(ImportConfig importConfig) {
		//The default charset = UTF-8.
		importConfig.setCharset(StandardCharsets.UTF_8);
		importConfig.setSqlOutputFile(importConfig.getSqlOutputUtf8File());

		createNewSqlFile(importConfig);
		generateAssuntoImportScripts(importConfig);
		generateUpdateHierarchyScript(importConfig);
		generateCommentedSelectScriptJustForCheck(importConfig);

		isAllAssuntosIdentified = true;

		if (importConfig.isToGenerateSqlIso88591()) {
			importConfig.setCharset(StandardCharsets.ISO_8859_1);
			importConfig.setSqlOutputFile(importConfig.getSqlOutputIsoFile());

			createNewSqlFile(importConfig);
			generateAssuntoImportScripts(importConfig);
			generateUpdateHierarchyScript(importConfig);
			generateCommentedSelectScriptJustForCheck(importConfig);
		}
	}

	private void createNewSqlFile(ImportConfig importConfig) {
		String header = "--This is a script to import new 'Assuntos' from CNJ's Excel.\n\n\n";
		FileUtil.createFile(importConfig.getSqlOutputFile(), importConfig.getCharset(), header);
	}

	private void generateAssuntoImportScripts(ImportConfig importConfig) {
		String template = FileUtil.getFileContentFromResource("sql_assunto_template.txt", importConfig.getCharset());
		List<Assunto> assuntosFomTempDb = assuntoRepository.findAll();
		int pesoValue =  getPesoValueField(importConfig);

		if (isAllAssuntosIdentified) {
			for (AssuntoDecorator assunto : newAssuntosToImport) {
				String sqlStmt = assunto.getSqlStmt(template, pesoValue);
				FileUtil.wrieteIntoFile(importConfig.getSqlOutputFile(), importConfig.getCharset(), sqlStmt.toString());
			}
		} else {
			for (Assunto assunto : assuntosFomTempDb) {
				Optional<AssuntoTrf> assuntoTrf = assuntoTrfRepository.findFirstByCodAssuntoTrf(assunto.getCodigo());
	
				if (!assuntoTrf.isPresent()) {
					//Add only if the "Assunto" is not in offical database.
					newAssuntosToImport.add(new AssuntoDecorator(assunto));
					String sqlStmt = assunto.getSqlStmt(template, pesoValue);
					FileUtil.wrieteIntoFile(importConfig.getSqlOutputFile(), importConfig.getCharset(), sqlStmt.toString());
				}
			}
		}
	}

	private int getPesoValueField(ImportConfig importConfig) {
		int pesoAssunto = 1; //1º Grau == 1.0

		if (importConfig.getGrau() == 2) {
			pesoAssunto = 2; //2º Grau == 2.0
		}
		return pesoAssunto;
	}

	private void generateUpdateHierarchyScript(ImportConfig importConfig) {
		String template = FileUtil.getFileContentFromResource("sql_assunto_update_template.txt", importConfig.getCharset());
		String codes = getSelectScript(false);
		String sqlStmt = template.replace(AssuntoFields.CODIGOS, codes);
		FileUtil.wrieteIntoFile(importConfig.getSqlOutputFile(), importConfig.getCharset(), sqlStmt);
	}

	/*
	 * This generates a commented script like this:
	 * 
	 *   / * 
	 *   SELECT * FROM client.tb_assunto_trf
	 *   WHERE cd_assunto_trf IN (
	 *     '123456', '123456', '123456', '123456', '123456', 
	 * 	   '123456', '123456', '123456', '123456', '123456'
	 *   );
	 * 	* /
	 * 
	 */
	private void generateCommentedSelectScriptJustForCheck(ImportConfig importConfig) {
		StringBuilder sqlStmt = new StringBuilder();
		sqlStmt.append("\n");
		sqlStmt.append("/* \n");
		sqlStmt.append(getSelectScript(true));
		sqlStmt.append(String.format("\n --Total = %d \n", newAssuntosToImport.size()));
		sqlStmt.append("\n */  \n");
		FileUtil.wrieteIntoFile(importConfig.getSqlOutputFile(), importConfig.getCharset(), sqlStmt.toString());
	}

	private String getSelectScript(boolean isSimpleQuote) {
		StringBuilder sqlStmt = new StringBuilder();
		int count = 0;

		sqlStmt.append("\nSELECT id_assunto_trf, cd_assunto_trf, cd_assunto_trf_superior");
		sqlStmt.append("\nFROM client.tb_assunto_trf");
		sqlStmt.append("\nWHERE cd_assunto_trf IN (");

		for (AssuntoDecorator assunto : newAssuntosToImport) {
			String code = isSimpleQuote ? assunto.getCodigoSimpleQuote() : assunto.getCodigoDoubleQuote();

			if (count == 0) {
				sqlStmt.append("\n").append(code);
			} else {
				if (count % 11 == 0) {
					sqlStmt.append(",\n").append(code);
				} else {
					sqlStmt.append(", ").append(code);
				}
			}
			count++;
		}
		sqlStmt.append("\n  );");

		return sqlStmt.toString();
	}

	public void generateCsvFile() {
		//TODO : not implemented
		/*
		if (importConfig.isToUseExcelFileAsFilter()) {
			importFilterExcelIntoTempDatabase(importConfig);
		}

		if (importConfig.isToGenerateCsvFileWithDataToAnalyses()) {
			generateOutputFileJustForAnalyses(importConfig);
		}
		*/
	}


	@Transactional("sgtTransactionManager")
	private void importFilterExcelIntoTempDatabase(ImportConfig importConfig) {
		String xlsFilterFile = importConfig.getXlsInputFilterFile();
		System.out.println(String.format("\n\n\nImporting data filter from '%s' into sgt database...", xlsFilterFile));

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream fis = classloader.getResourceAsStream(xlsFilterFile);
		XSSFWorkbook wb = null; //HSSFWorkbook wb = null;
		int rowCount = 0;

		try {
			wb = new XSSFWorkbook(fis); //wb = new HSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); //HSSFSheet sheet =  wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) { 
				Row row = rowIterator.next(); 
				rowCount++;

				if (isRowWithNoValidContent(rowCount, row)) {
					continue;
				}
				
				AssuntoAvaliado assuntoAvaliado = new AssuntoAvaliado();
				Iterator<Cell> cellIterator = row.cellIterator(); 
	
				while (cellIterator.hasNext()) { 
					String value = null;
					Cell cell = cellIterator.next(); 
					int excelColumnIndex = cell.getColumnIndex();

					switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							break;
						case NUMERIC:
							value = getCodigoAsString(cell.getNumericCellValue());
							break;
						default:
							break;
					}

					switch(excelColumnIndex) {
						case ExcelAvaliadoStructure.CODIGO_COLUMN:
							assuntoAvaliado.setCodigo(value);
							break;
						case ExcelAvaliadoStructure.DESCRICAO_COLUMN:
							assuntoAvaliado.setAssunto(value);
							break;
						case ExcelAvaliadoStructure.ABRANGENCIA_COLUMN:
							assuntoAvaliado.setAbrangencia(value);
							break;
					}
				}
				this.assuntoAvaliadoRepository.save(assuntoAvaliado);
			}
		} catch (Exception e) {
			System.out.println(String.format("\nERROR at line: %d", rowCount));
			e.printStackTrace();
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isRowWithNoValidContent(int rowCount, Row row) {
		//It ignores the sheet header and rows with 'codigo' column blank
		return rowCount < ExcelStructure.FIRST_ROW_OF_DATA || 
			row.getCell(ExcelStructure.CODIGO_COLUMN).getNumericCellValue() == 0;
	}

	private String getCodigoAsString(double codigo) {
		return String.valueOf(Math.round(codigo));
	}

	@Transactional("pjeTransactionManager")
	private void generateOutputFileJustForAnalyses(ImportConfig importConfig) {
		/*
		String csvFile = importConfig.getXlsInputFile();
		Charset charset = importConfig.getCharset();
		boolean isFilterActived = importConfig.isToUseDatabaseFilter();
		StringBuffer csvContent = new StringBuffer();
		String header = Assunto.getCsvHeader();
		int count = 0;
		List<Assunto> assuntosFomTempDb = assuntoRepository.findAll();

		for (Assunto assunto : assuntosFomTempDb) {
			Optional<AssuntoTrf> assuntoTrf = assuntoTrfRepository.findFirstByCodAssuntoTrf(assunto.getCodigo());

			if (!assuntoTrf.isPresent()) {
				if (isFilterActived) {
					Optional<AssuntoAvaliado> assuntoFilter = this.assuntoAvaliadoRepository.findFirstByCodigo(assunto.getCodigo());
					if (!assuntoFilter.isPresent()) {
						csvContent.append(assunto).append("\n");
						count++;
					}
				} else {
					csvContent.append(assunto).append("\n");
					count++;
				}
			}
		}
		CsvFileWritter.createFile(csvFile, charset, header, csvContent.toString());
		System.out.println(String.format("\n\n\nNumber of new assuntos: %d\n\n\n", count));
		*/
	}

}
