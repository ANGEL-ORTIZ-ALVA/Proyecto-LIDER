package com.springboot.lider.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.lider.models.entitys.Proveedor;


public interface IProveedoresDao {
	public Page<Proveedor> listar(Pageable pageable);
	public int guardar(Proveedor prov);
	public Proveedor buscarID(int id);
	public int actualizar (Proveedor prov);
	public int eliminar (int id);
	public int contarProv();

}
