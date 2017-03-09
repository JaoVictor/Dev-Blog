package com.dev.aplicacao.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dev.dominio.usuario.NovoUsuarioService;
import com.dev.dominio.usuario.UsuarioId;

@RunWith(MockitoJUnitRunner.class)
public class NovoUsuarioServicoAplicacaoTest {

	NovoUsuarioServicoAplicacao novoUsuarioServicoAplicacao;
	
	@Mock
	NovoUsuarioService novoUsuarioService;
	
	@Before
	public void setup(){
		novoUsuarioServicoAplicacao = new NovoUsuarioServicoAplicacao(novoUsuarioService);
	}
	
	@Test
	public void novoUsuario_DadoComando_NovoUsuarioServiceAcionado(){
		
		when(novoUsuarioService.novo(anyString(), anyString(), anyString()))
			.thenReturn(new UsuarioId("USUARIO-ID"));
		
		NovoUsuarioComando comando = new NovoUsuarioComando(
				"Nome",
				"Email",
				"senha");
		
		String usuarioId = novoUsuarioServicoAplicacao.novoUsuario(comando);
		
		verify(novoUsuarioService).novo(eq("Nome"), eq("Email"), eq("senha"));
		assertThat(usuarioId).isEqualTo("USUARIO-ID");
	}
	
	// TODO validar se nome esta vazio ou nulo
	// TODO validar se email esta vazio ou nulo
	// TODO validar se senha esta vazio ou nulo
	
}
