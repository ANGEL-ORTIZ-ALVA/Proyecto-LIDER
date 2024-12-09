package com.springboot.sorea.models.entitys;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Sorea {
	private int id;
	private int id_proveedor;
	private int recepcionista;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_solicitud;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_recepcion;
	private String descripcion;
	
	public Sorea(int id, int id_proveedor, int recepcionista, Date fecha_solicitud, Date fecha_recepcion,
			String descripcion) {
		super();
		this.id = id;
		this.id_proveedor = id_proveedor;
		this.recepcionista = recepcionista;
		this.fecha_solicitud = fecha_solicitud;
		this.fecha_recepcion = fecha_recepcion;
		this.descripcion = descripcion;
	}

	public Sorea() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public int getRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(int recepcionista) {
		this.recepcionista = recepcionista;
	}

	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public Date getFecha_recepcion() {
		return fecha_recepcion;
	}

	public void setFecha_recepcion(Date fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
