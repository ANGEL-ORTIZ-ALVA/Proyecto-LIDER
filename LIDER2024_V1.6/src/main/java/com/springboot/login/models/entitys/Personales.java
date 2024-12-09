package com.springboot.login.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personal")
public class Personales {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_personal;
	
	/*ESTABLECER INTEGRIDAD REFERENCIAL*/
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
	
	/*ESTABLECER INTEGRIDAD REFERENCIAL*/
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Roles id_rol;
	
	@Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;
}
