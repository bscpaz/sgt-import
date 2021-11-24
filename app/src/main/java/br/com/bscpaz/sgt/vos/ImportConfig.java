package br.com.bscpaz.sgt.vos;

import java.nio.charset.Charset;

public class ImportConfig {

	//Environment of execution. 
	private int grau = 1;
	
	//Indicates whether to skip import task from Excel file (use only the database's data).
	private boolean skipXlsInputFile = false;

	//Charset of output file
	private Charset charset = null;

	//The script with all import data.
	private String sqlOutputFile = null;

	//It indicates whether the output file needs to be filtered by a source already avaliated by business area.
	private boolean isToUseExcelFileAsFilter = false;

	//This indicates whether to generate a ISO 8859-1 version of SQL file.
	private boolean isToGenerateCsvFileWithDataToAnalyses = false;

	//This indicates whether to generate a ISO 8859-1 version of SQL file.
	private boolean isToGenerateSqlIso88591 = false;

	public ImportConfig(int grau) {
		this.grau = grau;
	}

	public String getXlsInputFile() {
		return Constants.XLS_INPUT_FILE;
	}

	public String getXlsInputFilterFile() {
		return Constants.XLS_FILTER_FILE;
	}

	public String getCsvOutputFile() {
		return Constants.CSV_OUTPUT_FILE;
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public int getGrau() {
		return grau;
	}

	public String getSqlOutputUtf8File() {
		if (this.grau == 1) {
			return Constants.SQL_OUTPUT_FILE_1G;
		}
		return Constants.SQL_OUTPUT_FILE_2G;
	}

	public String getSqlOutputIsoFile() {
		if (this.grau == 1) {
			return Constants.SQL_OUTPUT_FILE_1G_ISO;
		}
		return Constants.SQL_OUTPUT_FILE_2G_ISO;
	}

	public boolean isToGenerateSqlIso88591() {
		return isToGenerateSqlIso88591;
	}

	public void setToGenerateSqlIso88591(boolean isToGenerateSqlIso88591) {
		this.isToGenerateSqlIso88591 = isToGenerateSqlIso88591;
	}

	public String getSqlOutputFile() {
		return sqlOutputFile;
	}

	public void setSqlOutputFile(String sqlOutputFile) {
		this.sqlOutputFile = sqlOutputFile;
	}

	public boolean isToUseExcelFileAsFilter() {
		return isToUseExcelFileAsFilter;
	}

	public void setToUseExcelFileAsFilter(boolean isToUseExcelFileAsFilter) {
		this.isToUseExcelFileAsFilter = isToUseExcelFileAsFilter;
	}

	public boolean isToGenerateCsvFileWithDataToAnalyses() {
		return isToGenerateCsvFileWithDataToAnalyses;
	}

	public void setToGenerateCsvFileWithDataToAnalyses(boolean isToGenerateCsvFileWithDataToAnalyses) {
		this.isToGenerateCsvFileWithDataToAnalyses = isToGenerateCsvFileWithDataToAnalyses;
	}

	public boolean isSkipXlsInputFile() {
		return skipXlsInputFile;
	}

	public void setSkipXlsInputFile(boolean skipXlsInputFile) {
		this.skipXlsInputFile = skipXlsInputFile;
	}
	
}
