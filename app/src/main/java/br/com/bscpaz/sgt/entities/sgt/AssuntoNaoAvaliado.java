package br.com.bscpaz.sgt.entities.sgt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bscpaz.sgt.vos.Schema;

@Entity
@Table(name = AssuntoNaoAvaliado.TABLE_NAME, schema = Schema.PUBLIC)
public class AssuntoNaoAvaliado {
	
	public static final String TABLE_NAME = "tb_assuntos_nao_avaliados";

	@Id
	@Column(name = "cd_assunto_trf", unique = true, nullable = false)
	private String codigo;

	@Column(name = "cd_assunto_trf_superior")
	private String codigoSuperior;

	@Column(name = "ds_assunto_trf", nullable = false, length = 200)
	private String assunto;

	@Column(name = "ds_assunto_completo", nullable = false)
	private String assuntoCompleto;

	@Column(name = "ds_norma", length = 200)
	private String norma;

	@Column(name = "ds_lei_artigo")
	private String artigo;

	@Column(name = "ds_assunto_trf_glossario")
	private String glossario;

	@Column(name = "ds_alteracoes", length = 200)
	private String alteracoes;

	@Column(name = "num_grau", nullable = false)
	private Integer grau;

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

	public Integer getGrau() {
		return grau;
	}

	public void setGrau(Integer grau) {
		this.grau = grau;
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
		AssuntoNaoAvaliado other = (AssuntoNaoAvaliado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
	
}
