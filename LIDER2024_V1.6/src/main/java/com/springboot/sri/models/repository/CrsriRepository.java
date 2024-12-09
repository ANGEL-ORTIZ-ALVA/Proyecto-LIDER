package com.springboot.sri.models.repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.sri.models.entitys.Crsri;
import com.springboot.sri.models.entitys.Sri;

import jakarta.transaction.Transactional;

public interface CrsriRepository extends JpaRepository<Crsri, Integer>{
	
	//filtrar solicitudes aprobadas
	//List<Crsri> findByEstadoRecepcion(String estadoRecepcion);
	//public List<Crsri>findByEstado_recepcion(EstadoRecepcion estado_recepcion);
	
	boolean existsByIdSRI(Sri idSRI);
	
	
	public List<Crsri> findByIdSRI(Sri idSRI);
	
	
	//Procedimientos almacenados
	
	@Transactional
    @Modifying
    @Query(value = "CALL usp_insertar_crsri(:p_id_sri, :p_fecha_recepcion, :p_hora_recepcion, :p_personal_recepcion, :p_estado_recepcion, :p_descripcion)", nativeQuery = true)
    public void insertarCRSRI(
        @Param("p_id_sri") int p_id_sri,
        @Param("p_fecha_recepcion") Date p_fecha_recepcion,
        @Param("p_hora_recepcion") LocalTime p_hora_recepcion,
        @Param("p_personal_recepcion") int p_personal_recepcion,
        @Param("p_estado_recepcion") String p_estado_recepcion,
        @Param("p_descripcion") String p_descripcion
    );
	
	@Transactional
    @Modifying
    @Query(value = "CALL usp_editar_crsri(:id, :p_fecha_recepcion, :p_hora_recepcion, :p_personal_recepcion, :p_estado_recepcion, :p_descripcion)", nativeQuery = true)
    public void editarCRSRI(
        @Param("id") int id,
        @Param("p_fecha_recepcion") Date p_fecha_recepcion,
        @Param("p_hora_recepcion") LocalTime p_hora_recepcion,
        @Param("p_personal_recepcion") int p_personal_recepcion,
        @Param("p_estado_recepcion") String p_estado_recepcion,
        @Param("p_descripcion") String p_descripcion
    );

    @Transactional
    @Modifying
    @Query(value = "CALL usp_eliminar_crsri(:id)", nativeQuery = true)
    public void eliminarCRSRI(@Param("id") int id);

    @Query("SELECT COUNT(c) > 0 FROM Crsri c WHERE c.idSRI.id = :idSRI")
    public boolean existsBySRI(@Param("idSRI") int idSRI);
	

}

