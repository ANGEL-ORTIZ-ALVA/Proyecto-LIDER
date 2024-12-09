package com.springboot.sri.models.entitys;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name="SRI")
public class Sri {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_personal")
	private Personal personal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_soli_incidente", nullable = false)
	private SolicitudIncidente solicitudIncidente;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_solicitud")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_solicitud;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
    private Estado estado = Estado.pendiente;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_aprobacion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_aprobacion;
	
	@Column(columnDefinition = "TEXT", nullable = true)
    private String observaciones;
	
	 @Transient
	 private boolean hasCrsri;
	
	 
	
	// Enum para el estado
    public enum Estado {
        pendiente,
        aprobada,
        rechazada
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public SolicitudIncidente getSolicitudIncidente() {
		return solicitudIncidente;
	}

	public void setSolicitudIncidente(SolicitudIncidente solicitudIncidente) {
		this.solicitudIncidente = solicitudIncidente;
	}

	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFecha_aprobacion() {
		return fecha_aprobacion;
	}

	public void setFecha_aprobacion(Date fecha_aprobacion) {
		this.fecha_aprobacion = fecha_aprobacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the hasCrsri
	 */
	public boolean isHasCrsri() {
		return hasCrsri;
	}

	/**
	 * @param hasCrsri the hasCrsri to set
	 */
	public void setHasCrsri(boolean hasCrsri) {
		this.hasCrsri = hasCrsri;
	}
}
