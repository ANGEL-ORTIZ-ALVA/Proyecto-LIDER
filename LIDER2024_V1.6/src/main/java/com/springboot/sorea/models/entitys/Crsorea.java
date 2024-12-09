package com.springboot.sorea.models.entitys;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Crsorea {
	
	private int id;
    private int id_solicitud;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_recepcion;
    private BigDecimal monto_total;
    private String estado;
    private String descripcion;
    
	public Crsorea(int id, int id_solicitud, Date fecha_recepcion, BigDecimal monto_total, String estado,
			String descripcion) {
		super();
		this.id = id;
		this.id_solicitud = id_solicitud;
		this.fecha_recepcion = fecha_recepcion;
		this.monto_total = monto_total;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public Crsorea() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_solicitud() {
		return id_solicitud;
	}

	public void setId_solicitud(int id_solicitud) {
		this.id_solicitud = id_solicitud;
	}

	public Date getFecha_recepcion() {
		return fecha_recepcion;
	}

	public void setFecha_recepcion(Date fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}

	public BigDecimal getMonto_total() {
		return monto_total;
	}

	public void setMonto_total(BigDecimal monto_total) {
		this.monto_total = monto_total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}
