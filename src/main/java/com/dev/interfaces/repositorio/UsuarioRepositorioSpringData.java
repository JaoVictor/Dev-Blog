package com.dev.interfaces.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.dominio.usuario.Usuario;
import com.dev.dominio.usuario.UsuarioId;

public interface UsuarioRepositorioSpringData extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUsuarioId(UsuarioId usuarioId);
	Optional<Usuario> findByEmail(String email);

}
