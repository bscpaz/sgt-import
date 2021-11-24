package br.com.bscpaz.sgt.entities.sgt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bscpaz.sgt.vos.Schema;

@Entity
@Table(name = AssuntoAvaliado.TABLE_NAME, schema = Schema.PUBLIC)
public class AssuntoAvaliado {
	
	public static final String TABLE_NAME = "tb_assuntos_avaliados";

	@Id
	@Column(name = "cd_assunto_trf", unique = true, nullable = false)
	private String codigo;

	@Column(name = "ds_assunto_trf", nullable = false)
	private String assunto;

	@Column(name = "ds_abrangencia")
	private String abrangencia;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getAbrangencia() {
		return abrangencia;
	}

	public void setAbrangencia(String abrangencia) {
		this.abrangencia = abrangencia;
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
		AssuntoAvaliado other = (AssuntoAvaliado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}


	
}
