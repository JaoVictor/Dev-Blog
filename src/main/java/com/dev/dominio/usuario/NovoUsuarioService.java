package com.dev.dominio.usuario;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class NovoUsuarioService {

	public static final String ERR_EMAIL_JA_EXISTE = "Email já existe";
	
	private UsuarioRepositorio usuarioRepositorio;
	
	public NovoUsuarioService(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	public UsuarioId novo(String nome, String email, String senha) {
		
		if(usuarioRepositorio.obterPeloEmail(email).isPresent())
			throw new RuntimeException(ERR_EMAIL_JA_EXISTE);
		
		Usuario usuario = new Usuario(
				usuarioRepositorio.proximaIdentidade(), 
				nome, 
				email, 
				Usuario.NIVEL_USUARIO, 
				senha, 
				new Date(), 
				null, 
				false);
		
		usuarioRepositorio.salvar(usuario);
		return usuario.usuarioId();
		
	}
}
