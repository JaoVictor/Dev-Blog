package com.dev.interfaces.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

import com.dev.aplicacao.usuario.NovoUsuarioComando;
import com.dev.aplicacao.usuario.NovoUsuarioServicoAplicacao;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	NovoUsuarioServicoAplicacao novoUsuarioServicoAplicacao;

	public UsuarioController(
			NovoUsuarioServicoAplicacao novoUsuarioServicoAplicacao){
		
		this.novoUsuarioServicoAplicacao = novoUsuarioServicoAplicacao;
	};
	
	@PostMapping
	public HttpEntity<Void> nova(@RequestBody NovoUsuarioComando comando) {
		
		String usuarioId = novoUsuarioServicoAplicacao.novoUsuario(comando);

		UriComponents uriComponents = fromMethodCall(on(getClass()).obter(usuarioId)).build();

		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}
	

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<String> obter(@PathVariable String id){
//		PesquisaAgenciaPeloIdComando comando = new PesquisaAgenciaPeloIdComando(id);
//		return pesquisaAgenciaServicoAplicacao.obter(comando);
		return null;
	}
	
}
