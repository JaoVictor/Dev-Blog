package com.dev.interfaces.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.dominio.usuario.Usuario;
import com.dev.dominio.usuario.UsuarioId;
import com.dev.dominio.usuario.UsuarioRepositorio;

@Repository
@Transactional
public class UsuarioRepositorioJpa implements UsuarioRepositorio{
	
	@Autowired
	UsuarioRepositorioSpringData repositorio;
	
	@Override
	public void salvar(Usuario usuario) {
		repositorio.save(usuario);
	}

	@Override
	public Optional<Usuario> obterPeloId(UsuarioId usuarioId) {
		return repositorio.findByUsuarioId(usuarioId);
	}

	@Override
	public List<Usuario> obterTodos() {
		return repositorio.findAll();
	}

	@Override
	public Optional<Usuario> obterPeloEmail(String email) {
		return repositorio.findByEmail(email);
	}
}
