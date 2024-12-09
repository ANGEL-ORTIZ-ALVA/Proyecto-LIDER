package com.springboot.sri.models.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Insumos")
public class Insumo {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @Column(name = "nombre",nullable = false, length = 100)
	 private String nombre;

	    @Column(name = "descripcion", columnDefinition = "TEXT")
	    private String descripcion;

	    @Column(name = "cantidad_stock", nullable = false)
	    private int cantidad_stock;

	    @Column(name = "unidad_medida", length = 50)
	    private String unidad_medida;

	    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
	    private BigDecimal precio_unitario;

	    @Column(name = "id_proveedor")
	    private int id_proveedor;

	    @Temporal(TemporalType.DATE)
	    @Column(name = "fecha_ingreso")
	    private Date fecha_ingreso;

	    @Temporal(TemporalType.DATE)
	    @Column(name = "fecha_vencimiento")
	    private Date fecha_vencimiento;

	    @Column(name = "ubicacion_almacen", length = 255)
	    private String ubicacion_almacen;

	    @Column(columnDefinition = "TEXT")
	    private String observaciones;

}
