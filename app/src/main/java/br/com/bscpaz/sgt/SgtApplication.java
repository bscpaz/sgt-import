package br.com.bscpaz.sgt;

import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bscpaz.sgt.services.AssuntoService;
import br.com.bscpaz.sgt.vos.ImportConfig;

@SpringBootApplication
public class SgtApplication {

	@Value("${ENV_GRAU}")
	private int grau;

	@Value("${ENV_SKIP_EXCEL_IMPORT}")
	private boolean isSkipExcelImport;

	@Value("${ENV_USE_ARRAY_AS_FILTER}")
	private boolean isToUseArrayAsFilter;

	@Value("${ENV_REPORT_MODE_ENABLED}")
	private boolean isReportModeEnabled;

	@Autowired
	private AssuntoService assuntoService;

	public static void main(String[] args) {
		SpringApplication.run(SgtApplication.class, args);
		System.out.println("\n\n\nMission accomplished!\n\n");
	}
	
	@PostConstruct
	public void importFile() {
		System.out.println(String.format("\n\n\n\nImporting assuntos for grau '%d'...\n\n", this.grau));

		ImportConfig importConfig = new ImportConfig(this.grau);
		importConfig.setCharset(StandardCharsets.ISO_8859_1);
		importConfig.setSkipXlsInputFile(this.isSkipExcelImport);
		importConfig.setToUseArrayFileAsFilter(this.isToUseArrayAsFilter);

		if (this.isReportModeEnabled) {
			this.assuntoService.saveNewRecordsForAnalyses(importConfig);
		} else {
			this.assuntoService.doImport(importConfig);
		}
	}
}
