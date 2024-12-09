package com.springboot.lider.models.entitys;

import java.time.LocalDate;

public class Reabastecimiento {
	private int id;
	private LocalDate fecha;
	private int id_proveedor;
	private int recepcionista;
	private String insumo;
	private int cantidad;
	private String unidad_medida;
	private String estado;
	private String descripcion;
	
	public Reabastecimiento() {
	}

	public Reabastecimiento(int id, LocalDate fecha, int id_proveedor, int recepcionista, String insumo, int cantidad,
			String unidad_medida, String estado, String descripcion) {
		this.id = id;
		this.fecha = fecha;
		this.id_proveedor = id_proveedor;
		this.recepcionista = recepcionista;
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.unidad_medida = unidad_medida;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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

	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad_medida() {
		return unidad_medida;
	}

	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
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
