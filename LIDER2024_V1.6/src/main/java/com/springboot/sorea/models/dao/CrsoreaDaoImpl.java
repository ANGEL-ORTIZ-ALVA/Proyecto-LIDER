package com.springboot.sorea.models.dao;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.sorea.models.entitys.Crsorea;

@Repository
public class CrsoreaDaoImpl implements ICrsoreaDao{
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Crsorea crsorea) {
		String sql = "INSERT INTO CRSOREA (id_solicitud, fecha_recepcion, monto_total, estado, descripcion) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, crsorea.getId_solicitud(), crsorea.getFecha_recepcion(), crsorea.getMonto_total(), crsorea.getEstado(), crsorea.getDescripcion());
		
	}

	@Override
	public Crsorea findById(int id) {
        String sql = "SELECT * FROM CRSOREA WHERE id = ?";
        Crsorea crsorea = jdbcTemplate.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Crsorea.class));
		return crsorea;
	}


	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM CRSOREA WHERE id = ?";
        jdbcTemplate.update(sql, id);
	}

	@Override
	public Page<Crsorea> listarComprobantes(Pageable pageable) {
		String sql = "SELECT * FROM CRSOREA";
		List<Crsorea> listaReabastecimiento = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Crsorea.class));
		int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), listaReabastecimiento.size());
	    Page<Crsorea> reabastecimientoPage = new PageImpl<>(listaReabastecimiento.subList(start, end), pageable, listaReabastecimiento.size());
		return reabastecimientoPage;
	}

	@Override
	public int actualizar(Crsorea crsorea) {
		String sql = "UPDATE CRSOREA SET id_solicitud = ?, fecha_recepcion = ?, monto_total = ?, estado = ?, descripcion = ? WHERE id = ?";
        return jdbcTemplate.update(sql, crsorea.getId_solicitud(), crsorea.getFecha_recepcion(), crsorea.getMonto_total(), crsorea.getEstado(), crsorea.getDescripcion(), crsorea.getId());
	}

	@Override
	public boolean existsByIdSolicitud(int idSolicitud) {
		 String sql = "SELECT COUNT(*) FROM CRSOREA WHERE id_solicitud = ?";
	        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{idSolicitud}, Integer.class);
	        return count != null && count > 0;
	}

	@Override
	public Integer findBySolicitudId(int idSolicitud) {
		String sql = "SELECT id FROM CRSOREA WHERE id_solicitud = ?";
        List<Integer> ids = jdbcTemplate.query(sql, new Object[]{idSolicitud}, (rs, rowNum) -> rs.getInt("id"));
        return ids.isEmpty() ? null : ids.get(0);
	}

}
