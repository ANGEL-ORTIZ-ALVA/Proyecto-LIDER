package com.springboot.sri.models.entitys;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CRSRI")
public class Crsri {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_SRI")
	private Sri idSRI;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_recepcion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_recepcion;
	
	@Column(name = "hora_recepcion", nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horaRecepcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personal_recepcion")
    private Personal personal_recepcion;
	
	/*
	@Column(name = "estado_recepcion", nullable = false)
	@Enumerated(EnumType.STRING)
    private EstadoRecepcion estado_recepcion;*/
	@Column(name = "estado_recepcion", nullable = false)
	private String estado_recepcion;
	
	 @Column(name = "descripcion", columnDefinition = "TEXT")
	 private String descripcion;
}
