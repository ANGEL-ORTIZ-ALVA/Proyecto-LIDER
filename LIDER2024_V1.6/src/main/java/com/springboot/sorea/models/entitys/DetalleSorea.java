package com.springboot.sorea.models.entitys;

public class DetalleSorea {
	private int id_SOREA;
	private int id_insumo;
	private int cantidad;
	private String unidad_medida;
	private double precio_unitario;
	private String descripcion;
	
	public DetalleSorea(int id_SOREA, int id_insumo, int cantidad, String unidad_medida, double precio_unitario,
			String descripcion) {
		super();
		this.id_SOREA = id_SOREA;
		this.id_insumo = id_insumo;
		this.cantidad = cantidad;
		this.unidad_medida = unidad_medida;
		this.precio_unitario = precio_unitario;
		this.descripcion = descripcion;
	}

	public DetalleSorea() {
		super();
	}

	public int getId_SOREA() {
		return id_SOREA;
	}

	public void setId_SOREA(int id_SOREA) {
		this.id_SOREA = id_SOREA;
	}

	public int getId_insumo() {
		return id_insumo;
	}

	public void setId_insumo(int id_insumo) {
		this.id_insumo = id_insumo;
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

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
