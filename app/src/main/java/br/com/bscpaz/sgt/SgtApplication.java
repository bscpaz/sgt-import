package br.com.bscpaz.sgt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bscpaz.sgt.services.AssuntoService;

@SpringBootApplication
public class SgtApplication {

	private static final String XLS_FILE_NAME = "38_Tabela_Assuntos_Justica_Federal_1_Grau.xlsx";

	@Autowired
	private AssuntoService assuntoService;

	public static void main(String[] args) {
		System.out.println(String.format("\n\n\n\nProcessing '%s' file...", XLS_FILE_NAME));
		SpringApplication.run(SgtApplication.class, args);
		System.out.println("Mission accomplished!\nSELECT * FROM tb_assuntos;");
	}

	@PostConstruct
	public void importFile() {
		this.assuntoService.loadXSSExcelFileIntoDatabase(XLS_FILE_NAME);
	}
}
