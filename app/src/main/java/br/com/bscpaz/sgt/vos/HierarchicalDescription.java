package br.com.bscpaz.sgt.vos;

public class HierarchicalDescription {
	
	private final static int MAX_SIZE_DESCRIPTION_COLUMN = 5;

	private String[] hierarchicalDes = new String[MAX_SIZE_DESCRIPTION_COLUMN];
	
	public void setValue(int currentIndex, String assunto, String codigo) {
		if (currentIndex >= 0 && currentIndex < MAX_SIZE_DESCRIPTION_COLUMN) {
			//Each array spot should have the "description (code)" content.
			for (int i = 0; i < hierarchicalDes.length; i++) {
				if (i == currentIndex) {
					this.hierarchicalDes[i] = String.format("%s (%s)", assunto, codigo);
				}
				if (i > currentIndex) {
					this.hierarchicalDes[i] = null;
				}
			}
		}
	}

	public String getValue() {
		//Expected format: description (code) | description (code) | description (code)
		//i.e: "DIREITO TRIBUTÁRIO (14) | Obrigação Tributária (5978) | Capacidade Tributária (10539)"
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < hierarchicalDes.length; i++) {
			if (this.hierarchicalDes[i] != null) {
				String separator = "";
				if (i > 0) {
					separator = " | ";
				}
				sb.append(separator).append(this.hierarchicalDes[i]);
			} else {
				break;
			}
		}
		return sb.toString();
	}
}
