package com.springboot.sorea.models.entitys;

public class Proveedor {
	private int id;
	private String nombre_empresa;
	private String telefono;
	private String correo;
	private String direccion;
	private String rubro;
	private String info_extra;
	public Proveedor(int id, String nombre_empresa, String telefono, String correo, String direccion, String rubro,
			String info_extra) {
		super();
		this.id = id;
		this.nombre_empresa = nombre_empresa;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.rubro = rubro;
		this.info_extra = info_extra;
	}
	public Proveedor() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_empresa() {
		return nombre_empresa;
	}
	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	public String getInfo_extra() {
		return info_extra;
	}
	public void setInfo_extra(String info_extra) {
		this.info_extra = info_extra;
	}
}
