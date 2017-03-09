package com.dev.dominio.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NovoUsuarioServiceTest {

	NovoUsuarioService novoUsuarioService;
	
	@Mock
	UsuarioRepositorio usuarioRepositorio;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup(){
		novoUsuarioService = new NovoUsuarioService(usuarioRepositorio);
	}
	
	// TODO criptografar a senha
	// TODO verificar se o email ja existe
	
	@Test
	public void novoUsuario_DadosIniciais_novoUsuarioCriado(){
		
		novoUsuarioService.novo(
				"nome",
				"email@email.com.br",
				"senha");
		
		ArgumentCaptor<Usuario> argUsuario = ArgumentCaptor.forClass(Usuario.class);
		verify(usuarioRepositorio).salvar(argUsuario.capture());
		
		Usuario usuario = argUsuario.getValue();
		assertThat(usuario.nome()).isEqualTo("nome");
		assertThat(usuario.email()).isEqualTo("email@email.com.br");
		assertThat(usuario.senha()).isEqualTo("senha");
		assertThat(usuario.dataCadastro()).isNotNull();
		assertThat(usuario.dataConfirmacao()).isNull();
		assertThat(usuario.nivel()).isEqualTo(Usuario.NIVEL_USUARIO);
		assertThat(usuario.ativo()).isFalse();
	}
	
	@Test
	public void novoUsuario_DadoEmailJaExiste_Exception(){

		thrown.expect(RuntimeException.class);
		thrown.expectMessage(NovoUsuarioService.ERR_EMAIL_JA_EXISTE);
		
		when(usuarioRepositorio.obterPeloEmail("email@email.com.br"))
			.thenReturn(Optional.of(mock(Usuario.class)));
		
		novoUsuarioService.novo(
				"nome",
				"email@email.com.br",
				"senha");
		
	}
}