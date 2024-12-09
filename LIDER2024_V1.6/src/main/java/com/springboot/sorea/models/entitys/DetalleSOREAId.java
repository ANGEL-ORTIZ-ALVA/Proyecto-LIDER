package com.springboot.sorea.models.entitys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleSOREAId implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Column(name = "id_SOREA")
	private Integer id_SOREA;
	
	@Column(name = "id_insumo")
    private Integer id_insumo;

	

	public DetalleSOREAId(Integer id_SOREA, Integer id_insumo) {
		this.id_SOREA = id_SOREA;
		this.id_insumo = id_insumo;
	}

	public DetalleSOREAId() {
	}

	public Integer getId_SOREA() {
		return id_SOREA;
	}

	public void setId_SOREA(Integer id_SOREA) {
		this.id_SOREA = id_SOREA;
	}

	public Integer getId_insumo() {
		return id_insumo;
	}

	public void setId_insumo(Integer id_insumo) {
		this.id_insumo = id_insumo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_SOREA == null) ? 0 : id_SOREA.hashCode());
		result = prime * result + ((id_insumo == null) ? 0 : id_insumo.hashCode());
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
		DetalleSOREAId other = (DetalleSOREAId) obj;
		if (id_SOREA == null) {
			if (other.id_SOREA != null)
				return false;
		} else if (!id_SOREA.equals(other.id_SOREA))
			return false;
		if (id_insumo == null) {
			if (other.id_insumo != null)
				return false;
		} else if (!id_insumo.equals(other.id_insumo))
			return false;
		return true;
	}
}
