package br.com.bscpaz.sgt;

import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bscpaz.sgt.services.AssuntoService;
import br.com.bscpaz.sgt.vos.ImportConfig;

@SpringBootApplication
public class SgtApplication {

	private static final int GRAU = 2; //<<< GHANGE THIS VARIABLE AND DATASOURCE TOO.
	private static final boolean SKIP_EXCEL_IMPORT = true;
	private static final boolean USE_ARRAY_AS_FILTER = true;
	private static final boolean LOAD_NEW_DATA_FOR_BUSSINES_AREA = true;

	@Autowired
	private AssuntoService assuntoService;

	public static void main(String[] args) {
		System.out.println(String.format("\n\n\n\nImporting assuntos for grau '%d'...\n\n", GRAU));
		SpringApplication.run(SgtApplication.class, args);
		System.out.println("\n\n\nMission accomplished!\n\n");
	}

	@PostConstruct
	public void importFile() {
		ImportConfig importConfig = new ImportConfig(GRAU);
		importConfig.setCharset(StandardCharsets.ISO_8859_1);
		importConfig.setSkipXlsInputFile(SKIP_EXCEL_IMPORT);
		importConfig.setToUseArrayFileAsInput(USE_ARRAY_AS_FILTER);

		if (LOAD_NEW_DATA_FOR_BUSSINES_AREA) {
			this.assuntoService.saveNewRecordsForAnalyses(importConfig);
		} else {
			this.assuntoService.doImport(importConfig);
		}
	}
}
