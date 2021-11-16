package br.com.bscpaz.sgt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgtApplication.class, args);
	}

	@PostConstruct
	public static void importFile() throws IOException {
		readExcel();
	}

	private static void readExcel() throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream fis = classloader.getResourceAsStream("assuntos.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.iterator(); 
		
		while (rowIterator.hasNext()) { 
			Row row = rowIterator.next(); 
			Iterator<Cell> cellIterator = row.cellIterator(); 

			while (cellIterator.hasNext()) { 
				Cell cell = cellIterator.next(); 
				switch (cell.getCellType()) { 
					case STRING: 
						System.out.print(cell.getStringCellValue() + "\t"); 
						break; 
					case NUMERIC: 
						System.out.print(cell.getNumericCellValue() + "\t"); 
						break; 
					case BOOLEAN: 
						System.out.print(cell.getBooleanCellValue() + "\t"); 
						break; default : 
					} 
				} 
			System.out.println("");
		}
	}
}
