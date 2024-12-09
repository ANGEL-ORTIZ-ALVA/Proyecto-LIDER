package com.springboot.lider.models.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.lider.models.entitys.Insumo;

@Repository
public class InsumosDaoImpl implements IInsumosDao{
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Page<Insumo> listar(Pageable pageable) {
	    String sql = "SELECT * FROM insumos";
	    List<Insumo> listaInsumos = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Insumo.class));
	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), listaInsumos.size());
	    Page<Insumo> insumosPage = new PageImpl<>(listaInsumos.subList(start, end), pageable, listaInsumos.size());
	    return insumosPage;
	}

	
	@Override
	public int guardar(Insumo ins) {
		String sql = "INSERT INTO Insumos (nombre, descripcion, cantidad_stock, unidad_medida, precio_unitario, id_proveedor, fecha_ingreso, fecha_vencimiento, ubicacion_almacen, observaciones)\r\n"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		return jdbc.update(sql, ins.getNombre(), ins.getDescripcion(), ins.getCantidad_stock(), ins.getUnidad_medida(), ins.getPrecio_unitario(), ins.getId_proveedor(), ins.getFecha_ingreso(), ins.getFecha_vencimiento(), ins.getUbicacion_almacen(), ins.getObservaciones());
	}

	@Override
	public Insumo buscarID(int id) {
		String sql = "select * from insumos where id=?";
		Insumo ins = jdbc.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Insumo.class));
		return ins;
	}

	@Override
	public int actualizar(Insumo ins) {
		String sql = "UPDATE Insumos \r\n"
				+ "SET nombre=?, descripcion=?, cantidad_stock=?, unidad_medida=?, precio_unitario=?, id_proveedor=?, fecha_ingreso=?, fecha_vencimiento=?, ubicacion_almacen=?, observaciones=? \r\n"
				+ "WHERE id=?\r\n";
		return jdbc.update(sql, ins.getNombre(), ins.getDescripcion(), ins.getCantidad_stock(), ins.getUnidad_medida(), ins.getPrecio_unitario(), ins.getId_proveedor(), ins.getFecha_ingreso(), ins.getFecha_vencimiento(), ins.getUbicacion_almacen(), ins.getObservaciones(), ins.getId());
	}

	@Override
	public int borrar(int id) {
		String sql = "delete from insumos where id=?";
		return jdbc.update(sql,id);
	}

	@Override
	public int contarIns() {
		String sql = "SELECT COUNT(*) FROM insumos";
        return jdbc.queryForObject(sql, Integer.class);
	}
}
