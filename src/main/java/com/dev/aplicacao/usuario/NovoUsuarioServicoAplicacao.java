package com.dev.aplicacao.usuario;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dev.dominio.usuario.NovoUsuarioService;

@Service
@Transactional
public class NovoUsuarioServicoAplicacao {

	NovoUsuarioService novoUsuarioService;
	
	public NovoUsuarioServicoAplicacao(NovoUsuarioService novoUsuarioService) {
		this.novoUsuarioService = novoUsuarioService;
	}

	public String novoUsuario(NovoUsuarioComando comando) {
		
		return String.valueOf(novoUsuarioService.novo(
								comando.getNome(), 
								comando.getEmail(), 
								comando.getSenha()));	
	}
	
	public String obter(String usuarioId){
		return null;
	}
	
}
