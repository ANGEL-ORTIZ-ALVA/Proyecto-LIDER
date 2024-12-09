package com.springboot.sorea.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.sorea.models.entitys.Crsorea;

public interface ICrsoreaDao {
	public Page<Crsorea> listarComprobantes(Pageable pageable);
	public void save(Crsorea crsorea);
	public int actualizar (Crsorea crsorea);
    public Crsorea findById(int id);
    public void deleteById(int id);
    public boolean existsByIdSolicitud(int idSolicitud);
    public Integer findBySolicitudId(int idSolicitud);
}
