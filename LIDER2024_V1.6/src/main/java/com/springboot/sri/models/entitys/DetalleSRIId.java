package com.springboot.sri.models.entitys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleSRIId implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Column(name = "id_SRI")
	private Integer idSRI;
	
	@Column(name = "id_insumo")
    private Integer idInsumo;

	public DetalleSRIId(Integer idSRI, Integer idInsumo) {
		super();
		this.idSRI = idSRI;
		this.idInsumo = idInsumo;
	}

	public DetalleSRIId() {
		super();
	}

	public Integer getIdSRI() {
		return idSRI;
	}

	public void setIdSRI(Integer idSRI) {
		this.idSRI = idSRI;
	}

	public Integer getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idInsumo == null) ? 0 : idInsumo.hashCode());
		result = prime * result + ((idSRI == null) ? 0 : idSRI.hashCode());
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
		DetalleSRIId other = (DetalleSRIId) obj;
		if (idInsumo == null) {
			if (other.idInsumo != null)
				return false;
		} else if (!idInsumo.equals(other.idInsumo))
			return false;
		if (idSRI == null) {
			if (other.idSRI != null)
				return false;
		} else if (!idSRI.equals(other.idSRI))
			return false;
		return true;
	}
}