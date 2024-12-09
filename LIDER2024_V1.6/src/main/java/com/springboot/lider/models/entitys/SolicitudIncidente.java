package com.springboot.lider.models.entitys;

import java.sql.Date;

public class SolicitudIncidente {
	private int id;
    private int idCliente;
    private Date fechaSolicitud;
    private String incidente;
    private String estado;
    private Date fechaAprobacion;
    private Date fechaRechazo;
    private String observaciones;

    public SolicitudIncidente() {}

	public SolicitudIncidente(int id, int idCliente, Date fechaSolicitud, String incidente, String estado,
			Date fechaAprobacion, Date fechaRechazo, String observaciones) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.fechaSolicitud = fechaSolicitud;
		this.incidente = incidente;
		this.estado = estado;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaRechazo = fechaRechazo;
		this.observaciones = observaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getIncidente() {
		return incidente;
	}

	public void setIncidente(String incidente) {
		this.incidente = incidente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public Date getFechaRechazo() {
		return fechaRechazo;
	}

	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
