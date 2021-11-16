package br.com.bscpaz.sgt.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bscpaz.sgt.entities.Assunto;
import br.com.bscpaz.sgt.repositories.AssuntoRepository;
import br.com.bscpaz.sgt.utils.ExcelStructure;

@Service
public class AssuntoService {
	
	@Autowired
	private AssuntoRepository repository;

	public void loadXSSExcelFileIntoDatabase(String xlsFile) {
		this.repository.deleteAll();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream fis = classloader.getResourceAsStream(xlsFile);
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
				
				Assunto assunto = new Assunto();
				Iterator<Cell> cellIterator = row.cellIterator(); 
	
				while (cellIterator.hasNext()) { 
					String value = null;
					Cell cell = cellIterator.next(); 
					int columnIndex = cell.getColumnIndex();

					switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							break;
						case NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							break;
						default:
							break;
					}

					switch(columnIndex) {
						case ExcelStructure.DESCRICAO_1_COLUMN:
						case ExcelStructure.DESCRICAO_2_COLUMN:
						case ExcelStructure.DESCRICAO_3_COLUMN:
						case ExcelStructure.DESCRICAO_4_COLUMN:
						case ExcelStructure.DESCRICAO_5_COLUMN:
							if (assunto.getDescricao() == null && value != null && !value.trim().equals("")) {
								//'Descricao' is spread amoung those all columns.
								assunto.setDescricao(value);
							}
							break;

						case ExcelStructure.CODIGO_COLUMN:
							assunto.setCodigo(value);
							break;
						
						case ExcelStructure.CODIGO_SUP_COLUMN:
							assunto.setCodigoSuperior(value);
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
				this.repository.save(assunto);
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
}
