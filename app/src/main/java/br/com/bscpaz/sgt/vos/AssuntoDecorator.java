package br.com.bscpaz.sgt.vos;

import br.com.bscpaz.sgt.entities.sgt.Assunto;

public class AssuntoDecorator {

	private Assunto assunto;

	public AssuntoDecorator(Assunto assunto) {
		this.assunto = assunto;
	}

	public String getCodigo() {
		return this.assunto.getCodigo();
	}

	public String getCodigoSimpleQuote() {
		return "'" + this.assunto.getCodigo() + "'"; 
	}
	
	public String getCodigoDoubleQuote() {
		return "''" + this.assunto.getCodigo() + "''"; 
	}

	public String getSqlStmt(String template,int current, int total) {
		return this.assunto.getSqlStmt(template, current, total);
	}
}
