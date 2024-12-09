package com.springboot.lider.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.lider.models.entitys.Reabastecimiento;


public interface IReabastecimientoDao {
	public Page<Reabastecimiento> listar(Pageable pageable);
	public int guardar(Reabastecimiento rea);
	public Reabastecimiento buscarID(int id);
	public int actualizar (Reabastecimiento rea);
	public int borrar (int id);
	public int contarRea();
}

