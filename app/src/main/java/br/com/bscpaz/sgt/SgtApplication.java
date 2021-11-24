package br.com.bscpaz.sgt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bscpaz.sgt.services.AssuntoService;
import br.com.bscpaz.sgt.vos.Constants;
import br.com.bscpaz.sgt.vos.ImportConfig;

@SpringBootApplication
public class SgtApplication {

	private static final int GRAU = 1; //<<< GHANGE THIS VARIABLE.
	private static final boolean SKIP_EXCEL_IMPORT = true;
	private static final boolean IS_TO_GENERATE_ISO8859_1_VERSION = true; //<<< GHANGE THIS VARIABLE.

	@Autowired
	private AssuntoService assuntoService;

	public static void main(String[] args) {
		System.out.println(String.format("\n\n\n\nProcessing '%s' file...", Constants.XLS_INPUT_FILE));
		SpringApplication.run(SgtApplication.class, args);
		System.out.println("\n\n\nMission accomplished!\n\n");
	}

	@PostConstruct
	public void importFile() {
		ImportConfig importConfig = new ImportConfig(GRAU);
		importConfig.setSkipXlsInputFile(SKIP_EXCEL_IMPORT);
		importConfig.setToGenerateSqlIso88591(IS_TO_GENERATE_ISO8859_1_VERSION);

		this.assuntoService.doImport(importConfig);
	}
}
