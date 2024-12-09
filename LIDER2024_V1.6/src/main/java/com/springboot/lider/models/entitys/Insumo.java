package com.springboot.lider.models.entitys;

import java.time.LocalDate;

public class Insumo {
	
	private int id;
	private String nombre;
	private String descripcion;
	private int cantidad_stock;
	private String unidad_medida;
	private double precio_unitario;
	private int id_proveedor;
	private LocalDate fecha_ingreso;
	private LocalDate fecha_vencimiento;
	private String ubicacion_almacen;
	private String observaciones;
	
	public Insumo(int id, String nombre, String descripcion, int cantidad_stock, String unidad_medida,
			double precio_unitario, int id_proveedor, LocalDate fecha_ingreso, LocalDate fecha_vencimiento,
			String ubicacion_almacen, String observaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad_stock = cantidad_stock;
		this.unidad_medida = unidad_medida;
		this.precio_unitario = precio_unitario;
		this.id_proveedor = id_proveedor;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_vencimiento = fecha_vencimiento;
		this.ubicacion_almacen = ubicacion_almacen;
		this.observaciones = observaciones;
	}

	public Insumo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad_stock() {
		return cantidad_stock;
	}

	public void setCantidad_stock(int cantidad_stock) {
		this.cantidad_stock = cantidad_stock;
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

	public int getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public LocalDate getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(LocalDate fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public LocalDate getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public String getUbicacion_almacen() {
		return ubicacion_almacen;
	}

	public void setUbicacion_almacen(String ubicacion_almacen) {
		this.ubicacion_almacen = ubicacion_almacen;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}