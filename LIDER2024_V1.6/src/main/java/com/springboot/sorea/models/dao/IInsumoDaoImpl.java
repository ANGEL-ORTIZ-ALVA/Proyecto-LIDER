package com.springboot.sorea.models.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.sorea.models.entitys.Insumo;

@Repository
public class IInsumoDaoImpl implements IInsumoDao{
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Insumo> listarInsumos() {
		String sql = "select * from Insumos";
		List<Insumo>listaInsumos = jdbc.query(sql,BeanPropertyRowMapper.newInstance(Insumo.class));
		return listaInsumos;
	}
}	
