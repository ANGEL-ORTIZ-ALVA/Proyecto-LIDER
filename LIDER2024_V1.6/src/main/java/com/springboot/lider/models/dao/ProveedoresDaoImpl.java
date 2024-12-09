package com.springboot.lider.models.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.lider.models.entitys.Proveedor;

@Repository
public class ProveedoresDaoImpl implements IProveedoresDao{
	@Autowired
	private JdbcTemplate jdbc;
	@Override
	public Page<Proveedor> listar(Pageable pageable) {
		String sql = "select * from Proveedores";
		List<Proveedor> listaProveedores = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Proveedor.class));
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), listaProveedores.size());
		Page<Proveedor> proveedorPage = new PageImpl<>(listaProveedores.subList(start, end), pageable, listaProveedores.size());
		return proveedorPage;
	}

	@Override
	public int guardar(Proveedor prov) {
		String sql = "insert into Proveedores(nombre_empresa,telefono,correo,direccion,rubro,info_extra) values (?,?,?,?,?,?)";
		return jdbc.update(sql,prov.getNombre_empresa(), prov.getTelefono(), prov.getCorreo(), prov.getDireccion(),prov.getRubro(), prov.getInfo_extra());}

	@Override
	public Proveedor buscarID(int id) {
		String sql = "select * from Proveedores where id=?";
		Proveedor prov = jdbc.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Proveedor.class));
		return prov;
	}

	@Override
	public int actualizar(Proveedor prov) {
		String sql = "update Proveedores set nombre_empresa=?, telefono=?, correo=?, direccion=?, rubro=?, info_extra=? where id=?";
		return jdbc.update(sql, prov.getNombre_empresa(), prov.getTelefono(), prov.getCorreo(), prov.getDireccion(),prov.getRubro(), prov.getInfo_extra(), prov.getId());
	}

	@Override
	public int eliminar(int id) {
		String sql = "delete from Proveedores where id=?";
		return jdbc.update(sql,id);
	}

	@Override
	public int contarProv() {
		String sql = "select count(*) from Proveedores";
		return jdbc.queryForObject(sql, Integer.class);
	}
}
