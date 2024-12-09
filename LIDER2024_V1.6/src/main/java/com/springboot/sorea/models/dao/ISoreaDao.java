package com.springboot.sorea.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.sorea.models.entitys.Sorea;

public interface ISoreaDao {
	public Page<Sorea> listar(Pageable pageable);
	public int guardar(Sorea rea);
	public Sorea buscarID(int id);
	public int actualizar (Sorea rea);
	public int borrar (int id);
	public int contarRea();
}
