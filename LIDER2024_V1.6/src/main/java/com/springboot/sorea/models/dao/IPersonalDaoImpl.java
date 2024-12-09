package com.springboot.sorea.models.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
//import com.springboot.models.entitys.DetalleSorea;
import com.springboot.sorea.models.entitys.Personal;

@Repository
public class IPersonalDaoImpl implements IPersonalDao{
	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public List<Personal> listarRecepcionistas() {
		String sql = "select * from Personal";
		List<Personal>listaPersonal = jdbc.query(sql,BeanPropertyRowMapper.newInstance(Personal.class));
		return listaPersonal;
	}
}
