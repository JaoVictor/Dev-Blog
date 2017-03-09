package com.dev.dominio.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositorio {

	default UsuarioId proximaIdentidade(){
		return new UsuarioId(UUID.randomUUID().toString().toUpperCase());
	}
	
	void salvar(Usuario usuario);

	Optional<Usuario> obterPeloId(UsuarioId usuarioId);

	List<Usuario> obterTodos();

	Optional<Usuario> obterPeloEmail(String email);

}
