package com.springboot.sorea.models.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.sorea.models.entitys.Sorea;

@Repository
public class SoreaDaoImpl implements ISoreaDao{
	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Page<Sorea> listar(Pageable pageable) {
		String sql = "select * from SOREA";
		List<Sorea> listaReabastecimiento = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Sorea.class));
		int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), listaReabastecimiento.size());
	    Page<Sorea> reabastecimientoPage = new PageImpl<>(listaReabastecimiento.subList(start, end), pageable, listaReabastecimiento.size());
		return reabastecimientoPage;
	}

	@Override
	public int guardar(Sorea rea) {
		String sql = "INSERT INTO SOREA (id_proveedor, recepcionista, fecha_solicitud, fecha_recepcion, descripcion) VALUES\r\n"
				+ "(?, ?, ?, ?, ?)";
		return jdbc.update(sql, rea.getId_proveedor(), rea.getRecepcionista(), rea.getFecha_solicitud(), rea.getFecha_recepcion(), rea.getDescripcion());
	}

	@Override
	public Sorea buscarID(int id) {
		String sql = "select * from SOREA where id=?";
		Sorea rea = jdbc.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Sorea.class));
		return rea;
	}

	@Override
	public int actualizar(Sorea rea) {
		String sql = "UPDATE SOREA SET id_proveedor = ?, recepcionista = ?, fecha_solicitud = ?, fecha_recepcion = ?, descripcion = ? WHERE id = ?";
        return jdbc.update(sql, rea.getId_proveedor(), rea.getRecepcionista(), rea.getFecha_solicitud(), rea.getFecha_recepcion(), rea.getDescripcion(), rea.getId());
	}

	@Override
	public int borrar(int id) {
		String sql = "delete from SOREA where id=?";
		return jdbc.update(sql,id);
	}

	@Override
	public int contarRea() {
		String sql = "SELECT COUNT(*) FROM SOREA";
        return jdbc.queryForObject(sql, Integer.class);
	}
}