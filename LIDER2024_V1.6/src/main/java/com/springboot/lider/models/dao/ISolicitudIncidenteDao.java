package com.springboot.lider.models.dao;

import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.lider.models.entitys.SolicitudIncidente;

public interface ISolicitudIncidenteDao {
	public Page<SolicitudIncidente> listar(Pageable pageable);
    public int guardar(SolicitudIncidente solicitudIncidente);
    public SolicitudIncidente buscarPorId(int id);
    public int actualizar(SolicitudIncidente solicitudIncidente);
    public int eliminar(int id);
    public int contarSI();
    /*filtrado por fechas*/
    List<SolicitudIncidente> filtrarPorFecha(Date fechaInicio, Date fechaFin);
}
