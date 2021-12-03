package br.com.bscpaz.sgt.vos;

import java.nio.charset.Charset;

public class ImportConfig {

	//Environment of execution. 
	private int grau = 1;
	
	//Indicates whether to skip import task from Excel file (use only the database's data).
	private boolean skipXlsInputFile = false;

	//Indicates to use a SELECT as source. See array_filter.txt.
	private boolean isToUseArrayFileAsInput = false;

	//Charset of output file
	private Charset charset = null;

	//It indicates whether the output file needs to be filtered by a source already avaliated by business area.
	private boolean isToUseExcelFileAsFilter = false;

	//This indicates whether to generate a ISO 8859-1 version of SQL file.
	private boolean isToGenerateCsvFileWithDataToAnalyses = false;

	public ImportConfig(int grau) {
		this.grau = grau;
	}

	public String getXlsInputFile() {
		if (this.grau == 1) {
			return  Constants.XLS_INPUT_FILE_1G;
		}
		return Constants.XLS_INPUT_FILE_2G;
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

	public String getSqlOutputFile() {
		if (this.grau == 1) {
			return Constants.SQL_OUTPUT_FILE_1G;
		}
		return Constants.SQL_OUTPUT_FILE_2G;
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

	public boolean isToUseArrayFileAsInput() {
		return isToUseArrayFileAsInput;
	}

	public void setToUseArrayFileAsInput(boolean isToUseArrayFileAsInput) {
		this.isToUseArrayFileAsInput = isToUseArrayFileAsInput;
	}

}
