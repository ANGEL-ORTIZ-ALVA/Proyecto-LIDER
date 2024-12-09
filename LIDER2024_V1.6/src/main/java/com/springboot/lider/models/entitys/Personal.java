package com.springboot.lider.models.entitys;

public class Personal {
	private int id_personal;
	private int id_usuario;
	private String nombre;
	private String apellido;
	private int in_rol;
	public Personal(int id_personal, int id_usuario, String nombre, String apellido, int in_rol) {
		super();
		this.id_personal = id_personal;
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.in_rol = in_rol;
	}
	public Personal() {
		super();
	}
	public int getId_personal() {
		return id_personal;
	}
	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getIn_rol() {
		return in_rol;
	}
	public void setIn_rol(int in_rol) {
		this.in_rol = in_rol;
	}
}
