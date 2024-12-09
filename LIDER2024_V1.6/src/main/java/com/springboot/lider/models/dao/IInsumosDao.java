package com.springboot.lider.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.lider.models.entitys.Insumo;


public interface IInsumosDao {
	public Page<Insumo> listar(Pageable pageable);
	public int guardar(Insumo ins);
	public Insumo buscarID(int id);
	public int actualizar (Insumo ins);
	public int borrar (int id);
	public int contarIns();
}
