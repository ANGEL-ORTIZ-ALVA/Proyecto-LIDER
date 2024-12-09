package com.springboot.sri.models.entitys;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DetalleSRI")
public class DetalleSri {
	@EmbeddedId
	private DetalleSRIId id;
	
	@ManyToOne
    @JoinColumn(name = "id_SRI", nullable = false, insertable = false, updatable = false)
    private Sri sri;
	
	@ManyToOne
    @JoinColumn(name = "id_insumo", nullable = false, insertable = false, updatable = false)
    private Insumo insumo;
	
	@Column(name = "cantidad",nullable = false)
    private int cantidad;
	
	@Column(name = "descripcion",columnDefinition = "TEXT")
    private String descripcion;
	
}
