package com.springboot.sri.models.services;

import java.util.List;
import java.util.Optional;

import com.springboot.sri.models.entitys.Crsri;
import com.springboot.sri.models.entitys.Sri;

public interface ICrsriService {
	/*CRUD*/
	public List<Crsri>listarCrsri();
	public void guardarCrsri(Crsri cr);
	public void editarCrsri(Crsri cr);
	public Optional<Crsri>buscarId(int id);
	public void borrarCrsri (int id);
	Crsri encontrarPorId(int id);
	List<Crsri> findBySri(Sri idSRI);
	public boolean existsBySri(Sri sri);
	
	/*Transaccional con actualizacion de stock*/
	public Crsri save(Crsri crsri);
	public Crsri update(Crsri crsri);
	public void delete(int id);
}
