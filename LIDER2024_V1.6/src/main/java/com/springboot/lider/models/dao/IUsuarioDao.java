package com.springboot.lider.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.lider.models.entitys.Usuario;

public interface IUsuarioDao {
	public Page<Usuario> listar(Pageable pageable);
    public int guardar(Usuario usuario);
    public Usuario buscarPorId(int id);
    public int actualizar(Usuario usuario);
    public int eliminar(int id);
    public int contarUsu();
}
