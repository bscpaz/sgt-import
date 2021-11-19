package br.com.bscpaz.sgt.entities.sgt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Assunto.TABLE_NAME, schema = "public")
public class Assunto {
	
	public static final String TABLE_NAME = "tb_assuntos";

	@Id
	@Column(name = "cd_assunto_trf", unique = true, nullable = false)
	private String codigo;

	@Column(name = "cd_assunto_trf_superior")
	private String codigoSuperior;

	@Column(name = "ds_assunto_trf", nullable = false)
	private String assunto;

	@Column(name = "ds_assunto_completo", nullable = false)
	private String assuntoCompleto;

	@Column(name = "ds_norma")
	private String norma;

	@Column(name = "ds_lei_artigo")
	private String artigo;

	@Column(name = "ds_assunto_trf_glossario")
	private String glossario;

	@Column(name = "ds_alteracoes")
	private String alteracoes;	
	
	@Column(name = "in_riscado")
	private Boolean riscado = Boolean.FALSE;

	@Column(name = "in_possui_filhos")
	private Boolean possuiFilhos = Boolean.FALSE;

	@Column(name = "in_importado")
	private Boolean importado = Boolean.FALSE;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoSuperior() {
		return codigoSuperior;
	}

	public void setCodigoSuperior(String codigoSuperior) {
		this.codigoSuperior = codigoSuperior;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getAssuntoCompleto() {
		return assuntoCompleto;
	}

	public void setAssuntoCompleto(String assuntoCompleto) {
		this.assuntoCompleto = assuntoCompleto;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getArtigo() {
		return artigo;
	}

	public void setArtigo(String artigo) {
		this.artigo = artigo;
	}

	public String getGlossario() {
		return glossario;
	}

	public void setGlossario(String glossario) {
		this.glossario = glossario;
	}

	public String getAlteracoes() {
		return alteracoes;
	}

	public void setAlteracoes(String alteracoes) {
		this.alteracoes = alteracoes;
	}

	public Boolean getRiscado() {
		return riscado;
	}

	public void setRiscado(Boolean riscado) {
		this.riscado = riscado;
	}

	public Boolean getPossuiFilhos() {
		return possuiFilhos;
	}

	public void setPossuiFilhos(Boolean possuiFilhos) {
		this.possuiFilhos = possuiFilhos;
	}

	public Boolean getImportado() {
		return importado;
	}

	public void setImportado(Boolean importado) {
		this.importado = importado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assunto other = (Assunto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assunto [assunto=" + assunto + ", assuntoCompleto=" + assuntoCompleto + ", codigo=" + codigo
				+ ", codigoSuperior=" + codigoSuperior + "]";
	}
}
