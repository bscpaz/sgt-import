package br.com.bscpaz.sgt.entities.sgt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bscpaz.sgt.vos.AssuntoFields;
import br.com.bscpaz.sgt.vos.Schema;

@Entity
@Table(name = Assunto.TABLE_NAME, schema = Schema.PUBLIC)
public class Assunto {
	
	public static final String TABLE_NAME = "tb_assuntos";

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
		if (assunto != null) {
			if (assunto.length() <= 200) {
				this.assunto = assunto;
			} else {
				System.out.println(
					String.format("ERROR: 'assunto' field has more then 200 characteres for Assunto = [%s].\nValue = %s", 
					this.codigo, assunto));
			}
		}		
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
		if (norma != null) {
			if (norma.length() <= 200) {
				this.norma = norma;
			} else {
				System.out.println(
					String.format("WARN: 'norma' field has more then 200 characteres for Assunto = [%s]. It will be a null value.", 
					this.codigo));
			}
		}
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
		if (alteracoes != null) {
			if (alteracoes.length() <= 200) {
				this.alteracoes = alteracoes;
			} else {
				System.out.println(
					String.format("WARN: 'alteracoes' field has more then 200 characteres for Assunto = [%s]. It will be a null value.", 
					this.codigo));
			}
		}
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

	public AssuntoNaoAvaliado getAssuntoNaoAvaliado(Integer grau) {
		AssuntoNaoAvaliado assuntoNaoAvaliado = new AssuntoNaoAvaliado();
		assuntoNaoAvaliado.setCodigo(this.codigo);
		assuntoNaoAvaliado.setCodigoSuperior(this.codigoSuperior);
		assuntoNaoAvaliado.setAssunto(this.assunto);
		assuntoNaoAvaliado.setAlteracoes(this.alteracoes);
		assuntoNaoAvaliado.setArtigo(this.artigo);
		assuntoNaoAvaliado.setAssuntoCompleto(this.assuntoCompleto);
		assuntoNaoAvaliado.setGlossario(this.glossario);
		assuntoNaoAvaliado.setNorma(this.norma);
		assuntoNaoAvaliado.setGrau(grau);
		return assuntoNaoAvaliado;
	}

	public AssuntoCarga getAssuntoCarga(Integer grau) {
		AssuntoCarga assuntoCarga = new AssuntoCarga();
		assuntoCarga.setCodigo(this.codigo);
		assuntoCarga.setCodigoSuperior(this.codigoSuperior);
		assuntoCarga.setAssunto(this.assunto);
		assuntoCarga.setAlteracoes(this.alteracoes);
		assuntoCarga.setArtigo(this.artigo);
		assuntoCarga.setAssuntoCompleto(this.assuntoCompleto);
		assuntoCarga.setGlossario(this.glossario);
		assuntoCarga.setNorma(this.norma);
		assuntoCarga.setGrau(grau);
		return assuntoCarga;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	public String getSqlStmt(String template, int current, int total) {
		template = template.replace(AssuntoFields.CURRENT, String.valueOf(current));
		template = template.replace(AssuntoFields.TOTAL, String.valueOf(total));
		template = template.replace(AssuntoFields.CODIGO, "'" + this.codigo + "'");

		if (this.codigoSuperior == null) {
			template = template.replace(AssuntoFields.CODIGO_SUP, "null");
		} else {
			template = template.replace(AssuntoFields.CODIGO_SUP, "'" + this.codigoSuperior + "'");
		}

		template = template.replace(AssuntoFields.ASSUNTO, "'" + this.assunto + "'");
		template = template.replace(AssuntoFields.ASSUNTO_COMPLETO, "'" + this.assuntoCompleto + "'");

		if (this.norma == null) {
			template = template.replace(AssuntoFields.NORMA, "null");
			template = template.replace(AssuntoFields.LEI, "null"); //LEI is the same as NORMA.
		} else {
			if (this.norma.length() <= 200) {
				template = template.replace(AssuntoFields.NORMA, "'" + this.norma + "'");
				template = template.replace(AssuntoFields.LEI, "'" + this.norma + "'"); //LEI is the same as NORMA.
			} else {
				template = template.replace(AssuntoFields.NORMA, "null");
				template = template.replace(AssuntoFields.LEI, "null"); //LEI is the same as NORMA.
			}
		}

		if (this.artigo == null) {
			template = template.replace(AssuntoFields.ARTIGO, "null");
		} else {
			template = template.replace(AssuntoFields.ARTIGO, "'" + this.artigo + "'");
		}

		template = template.replace(AssuntoFields.ARTIGO, "'" + this.artigo + "'");

		if (this.glossario == null) {
			template = template.replace(AssuntoFields.GLOSSARIO, "null");
		} else {
			template = template.replace(AssuntoFields.GLOSSARIO, "'" + this.glossario + "'");
		}
		template = template + "\n--=======================================================\n\n";
		return template.toString();
	}

	public static String getCsvHeader() {
		return "cod_assunto;assunto;cod_assunto_sup;assunto_completo";
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
		return codigo + ";" + assunto + ";" + codigoSuperior + ";" + assuntoCompleto; 
	}
}