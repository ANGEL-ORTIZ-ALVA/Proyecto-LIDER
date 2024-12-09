package com.springboot.login.models.entitys;

import java.sql.Timestamp;

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
@Table(name = "usuarios")
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	@Column(name = "contrasena")
	private String contrasena;

	@Column(name = "correo_electronico")
	private String correoElectronico;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
    
    /*TIPO DE US Y ESTADO*/
    public enum TipoUsuario {
    	cliente, trabajador, jefe_de_almacen, administrador
	 }
	 public enum Estado {
		 activo, suspendido
	 }
}
