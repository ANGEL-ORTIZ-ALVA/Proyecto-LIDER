package com.springboot.lider.models.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.springboot.lider.models.entitys.Reabastecimiento;

@Repository

public class ReabastecimientoDaoImpl implements IReabastecimientoDao{
	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Page<Reabastecimiento> listar(Pageable pageable) {
		String sql = "select * from Solicitud_de_Reabastecimiento";
		List<Reabastecimiento> listaReabastecimiento = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Reabastecimiento.class));
		int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), listaReabastecimiento.size());
	    Page<Reabastecimiento> reabastecimientoPage = new PageImpl<>(listaReabastecimiento.subList(start, end), pageable, listaReabastecimiento.size());
		return reabastecimientoPage;
	}

	@Override
	public int guardar(Reabastecimiento rea) {
		String sql = "INSERT INTO Solicitud_de_Reabastecimiento (fecha, id_proveedor, recepcionista, insumo, cantidad, unidad_medida, estado, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbc.update(sql, rea.getFecha(), rea.getId_proveedor(), rea.getRecepcionista(), rea.getInsumo(), rea.getCantidad(), rea.getUnidad_medida(), rea.getEstado(), rea.getDescripcion());
	}

	@Override
	public Reabastecimiento buscarID(int id) {
		String sql = "select * from Solicitud_de_Reabastecimiento where id=?";
		Reabastecimiento rea = jdbc.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Reabastecimiento.class));
		return rea;
	}

	@Override
	public int actualizar(Reabastecimiento rea) {
		String sql = "UPDATE Solicitud_de_Reabastecimiento SET fecha = ?, id_proveedor = ?, recepcionista = ?, insumo = ?, cantidad = ?, unidad_medida = ?, estado = ?, descripcion = ? WHERE id = ?";
        return jdbc.update(sql, rea.getFecha(), rea.getId_proveedor(), rea.getRecepcionista(), rea.getInsumo(), rea.getCantidad(), rea.getUnidad_medida(), rea.getEstado(), rea.getDescripcion(), rea.getId());
	}

	@Override
	public int borrar(int id) {
		String sql = "delete from Solicitud_de_Reabastecimiento where id=?";
		return jdbc.update(sql,id);
	}

	@Override
	public int contarRea() {
		String sql = "SELECT COUNT(*) FROM Solicitud_de_Reabastecimiento";
        return jdbc.queryForObject(sql, Integer.class);
	}
}