package com.springboot.sri.models.entitys;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Solicitud_incidente")
public class SolicitudIncidente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "id_cliente")
	private int id_cliente;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;
	
	@Column(name = "incidente")
	private String incidente;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
    private Estado estado = Estado.pendiente;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "fecha_aprobacion")
    private Date fecha_aprobacion;
	
	 @Temporal(TemporalType.DATE)
	 @Column(name = "fecha_rechazo")
	 private Date fecha_rechazo;
	 
	 @Column(columnDefinition = "TEXT")
	 private String observaciones;
	
	 @ManyToOne
	 @JoinColumn(name = "delegacion")
	 private Personal delegacion;
	
	// Enum para el estado
    public enum Estado {
        pendiente,
        aprobada,
        rechazada
    }

}
