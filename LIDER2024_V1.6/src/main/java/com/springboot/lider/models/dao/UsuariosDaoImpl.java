package com.springboot.lider.models.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.springboot.lider.models.entitys.Usuario;

@Repository
public class UsuariosDaoImpl implements IUsuarioDao{
	@Autowired
    private JdbcTemplate jdbc;

    @Override
    public Page<Usuario> listar(Pageable pageable) {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> listaUsuarios = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Usuario.class));
        int start =  (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), listaUsuarios.size());
        Page<Usuario> usuariosPage = new PageImpl<>(listaUsuarios.subList(start, end), pageable, listaUsuarios.size());
        return usuariosPage;
    }

    @Override
    public int guardar(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombre_usuario, contrasena, correo_electronico, telefono, tipo_usuario, estado, fecha_creacion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        return jdbc.update(sql, usuario.getNombreUsuario(), usuario.getContrasena(), usuario.getCorreoElectronico(),
                           usuario.getTelefono(), usuario.getTipoUsuario(), usuario.getEstado());
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        Usuario usuario = jdbc.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Usuario.class));
        return usuario;
    }

    @Override
    public int actualizar(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nombre_usuario=?, contrasena=?, correo_electronico=?, telefono=?, tipo_usuario=?, estado=? " +
                     "WHERE id=?";
        return jdbc.update(sql, usuario.getNombreUsuario(), usuario.getContrasena(), usuario.getCorreoElectronico(),
                           usuario.getTelefono(), usuario.getTipoUsuario(), usuario.getEstado(), usuario.getId());
    }


    @Override
    public int eliminar(int id) {
        String sql = "DELETE FROM Usuarios WHERE id=?";
        return jdbc.update(sql, id);
    }
    
    @Override
	public int contarUsu() {
		String sql = "SELECT COUNT(*) FROM Usuarios";
        return jdbc.queryForObject(sql, Integer.class);
	}

}
