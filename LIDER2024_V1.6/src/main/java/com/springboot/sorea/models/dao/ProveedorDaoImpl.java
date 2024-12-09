package com.springboot.sorea.models.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import com.springboot.models.entitys.Personal;
import com.springboot.sorea.models.entitys.Proveedor;

@Repository
public class ProveedorDaoImpl implements IProveedorDao{
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Proveedor> listarProveedores() {
		String sql = "select * from Proveedores";
		List<Proveedor>listaProveedor = jdbc.query(sql,BeanPropertyRowMapper.newInstance(Proveedor.class));
		return listaProveedor;
	}
}
