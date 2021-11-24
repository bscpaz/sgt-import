package br.com.bscpaz.sgt.entities.pje;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.com.bscpaz.sgt.vos.Schema;

@Entity
@Table(name = AssuntoTrf.TABLE_NAME, schema = Schema.CLIENT)
public class AssuntoTrf {
	
	public static final String TABLE_NAME = "tb_assunto_trf";

	@Id
	@Column(name = "id_assunto_trf", unique = true, nullable = false)
	private Integer idAssuntoTrf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_assunto_trf_superior")
	private AssuntoTrf assuntoTrfSuperior;

	@Column(name = "cd_assunto_trf", nullable = false, length = 30)
	private String codAssuntoTrf;
	
	@Column(name = "cd_assunto_trf_superior", length = 30)
	private String codAssuntoTrfSuperior;

	@Column(name = "ds_assunto_trf", nullable = false, length = 200)
	private String assuntoTrf;

	@Lob
	@Column(name = "ds_assunto_completo")
	@Type(type = "org.hibernate.type.TextType")
	@Basic(fetch=FetchType.LAZY)
	private String assuntoCompleto;

	@Column(name = "vl_peso", nullable = false)
	private Double valorPeso = 1d;

	@Column(name = "ds_norma", length = 200)
	private String norma;

	@Lob
	@Column(name = "ds_lei_artigo")
	@Type(type = "org.hibernate.type.TextType")
	@Basic(fetch=FetchType.LAZY)
	private String leiArtigo;

	@Lob
	@Column(name = "ds_lei")
	@Type(type = "org.hibernate.type.TextType")
	@Basic(fetch=FetchType.LAZY)
	private String lei;

	@Lob
	@Column(name = "ds_assunto_trf_glossario")
	@Type(type = "org.hibernate.type.TextType")
	@Basic(fetch = FetchType.LAZY)
	private String assuntoTrfGlossario;

	@Column(name = "in_possui_filhos")
	private Boolean possuiFilhos = Boolean.FALSE;

	@Column(name = "in_ativo", nullable = false)
	private Boolean ativo = Boolean.TRUE;

	@Column(name = "in_pss", nullable = false)
	private Boolean pss = Boolean.FALSE;

	@Column(name = "in_exige_nm", nullable = false)
	private boolean inExigeNM = Boolean.FALSE;

	@Column(name = "in_crime_antecedente", nullable = false)
	private Boolean exigeAssuntoAntecedente = Boolean.FALSE;

	@Column(name="in_padrao_sgt", nullable = false)
	private Boolean padraoSgt = Boolean.FALSE;


	
	public Integer getIdAssuntoTrf() {
		return idAssuntoTrf;
	}

	public void setIdAssuntoTrf(Integer idAssuntoTrf) {
		this.idAssuntoTrf = idAssuntoTrf;
	}

	public AssuntoTrf getAssuntoTrfSuperior() {
		return assuntoTrfSuperior;
	}

	public void setAssuntoTrfSuperior(AssuntoTrf assuntoTrfSuperior) {
		this.assuntoTrfSuperior = assuntoTrfSuperior;
	}

	public String getCodAssuntoTrf() {
		return codAssuntoTrf;
	}

	public void setCodAssuntoTrf(String codAssuntoTrf) {
		this.codAssuntoTrf = codAssuntoTrf;
	}

	public String getCodAssuntoTrfSuperior() {
		return codAssuntoTrfSuperior;
	}

	public void setCodAssuntoTrfSuperior(String codAssuntoTrfSuperior) {
		this.codAssuntoTrfSuperior = codAssuntoTrfSuperior;
	}

	public String getAssuntoTrf() {
		return assuntoTrf;
	}

	public void setAssuntoTrf(String assuntoTrf) {
		this.assuntoTrf = assuntoTrf;
	}

	public String getAssuntoCompleto() {
		return assuntoCompleto;
	}

	public void setAssuntoCompleto(String assuntoCompleto) {
		this.assuntoCompleto = assuntoCompleto;
	}

	public Double getValorPeso() {
		return valorPeso;
	}

	public void setValorPeso(Double valorPeso) {
		this.valorPeso = valorPeso;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getLeiArtigo() {
		return leiArtigo;
	}

	public void setLeiArtigo(String leiArtigo) {
		this.leiArtigo = leiArtigo;
	}

	public String getLei() {
		return lei;
	}

	public void setLei(String lei) {
		this.lei = lei;
	}

	public String getAssuntoTrfGlossario() {
		return assuntoTrfGlossario;
	}

	public void setAssuntoTrfGlossario(String assuntoTrfGlossario) {
		this.assuntoTrfGlossario = assuntoTrfGlossario;
	}

	public Boolean getPossuiFilhos() {
		return possuiFilhos;
	}

	public void setPossuiFilhos(Boolean possuiFilhos) {
		this.possuiFilhos = possuiFilhos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getPss() {
		return pss;
	}

	public void setPss(Boolean pss) {
		this.pss = pss;
	}

	public boolean isInExigeNM() {
		return inExigeNM;
	}

	public void setInExigeNM(boolean inExigeNM) {
		this.inExigeNM = inExigeNM;
	}

	public Boolean getExigeAssuntoAntecedente() {
		return exigeAssuntoAntecedente;
	}

	public void setExigeAssuntoAntecedente(Boolean exigeAssuntoAntecedente) {
		this.exigeAssuntoAntecedente = exigeAssuntoAntecedente;
	}

	public Boolean getPadraoSgt() {
		return padraoSgt;
	}

	public void setPadraoSgt(Boolean padraoSgt) {
		this.padraoSgt = padraoSgt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAssuntoTrf == null) ? 0 : idAssuntoTrf.hashCode());
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
		AssuntoTrf other = (AssuntoTrf) obj;
		if (idAssuntoTrf == null) {
			if (other.idAssuntoTrf != null)
				return false;
		} else if (!idAssuntoTrf.equals(other.idAssuntoTrf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssuntoTrf [codAssuntoTrf=" + codAssuntoTrf + ", assuntoTrf=" + assuntoTrf + ", ativo=" + ativo + "]";
	}

}