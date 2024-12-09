package com.springboot.sorea.models.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.springboot.sorea.models.entitys.DetalleSorea;

@Repository
public class DetalleSoreaDaoimpl implements IDetalleSoreaDao{
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<DetalleSorea> listarDetalleSorea() {
		String sql = "select * from DetalleSOREA";
		List<DetalleSorea>listaDetalle = jdbc.query(sql,BeanPropertyRowMapper.newInstance(DetalleSorea.class));
		return listaDetalle;
	}

	@Override
	public void guardarDetalleSorea(DetalleSorea detalleSorea) {
	    String sql = "INSERT INTO DetalleSOREA (id_SOREA, id_insumo, cantidad, unidad_medida, precio_unitario, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
	    jdbc.update(sql, detalleSorea.getId_SOREA(), detalleSorea.getId_insumo(), detalleSorea.getCantidad(), detalleSorea.getUnidad_medida(), detalleSorea.getPrecio_unitario(), detalleSorea.getDescripcion());
	}
	
	

	@Override
	public DetalleSorea buscarDetalleSorea(int idSorea, int idInsumo) {
	    String sql = "SELECT * FROM DetalleSOREA WHERE id_SOREA = ? AND id_insumo = ?";
	    return jdbc.queryForObject(sql, new Object[]{idSorea, idInsumo}, BeanPropertyRowMapper.newInstance(DetalleSorea.class));
	}
	
	@Override
	public DetalleSorea buscarDetalleSoreaPorIdSorea(int idSorea) {
		String sql = "SELECT * FROM DetalleSOREA WHERE id_SOREA = ? AND id_insumo = ?";
	    return jdbc.queryForObject(sql, new Object[]{idSorea}, BeanPropertyRowMapper.newInstance(DetalleSorea.class));
	}

	@Override
	public int actualizarDetalleSorea(DetalleSorea detalleSorea) {
	    String sql = "UPDATE DetalleSOREA SET cantidad = ?, unidad_medida = ?, precio_unitario = ?, descripcion = ? WHERE id_SOREA = ? AND id_insumo = ?";
	    return jdbc.update(sql, detalleSorea.getCantidad(), detalleSorea.getUnidad_medida(), detalleSorea.getPrecio_unitario(), detalleSorea.getDescripcion(), detalleSorea.getId_SOREA(), detalleSorea.getId_insumo());
	}

	@Override
	public int borrarDetalleSorea(int idSorea, int idInsumo) {
	    String sql = "DELETE FROM DetalleSOREA WHERE id_SOREA = ? AND id_insumo = ?";
	    return jdbc.update(sql, idSorea, idInsumo);
	}
	
	public List<DetalleSorea> obtenerProductosPorSolicitud(int id_SOREA) {
        String sql = "SELECT * FROM DetalleSOREA WHERE id_SOREA = ?";
        List<DetalleSorea> listaDetalleSorea =  jdbc.query(sql, BeanPropertyRowMapper.newInstance(DetalleSorea.class), id_SOREA);
        return listaDetalleSorea;
    }

	
}