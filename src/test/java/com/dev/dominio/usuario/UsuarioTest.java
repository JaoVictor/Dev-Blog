package com.dev.dominio.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UsuarioTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void novoUsuario(){
		
		Date data = new Date();
		
		Usuario usuario = new Usuario(
				new UsuarioId("USU-ID"),
				"Nome usuário",
				"usuario@email.com",
				Usuario.NIVEL_USUARIO,
				"senha",
				data,
				null,
				false);
				
		assertThat(usuario.usuarioId()).isEqualTo(new UsuarioId("USU-ID"));
		assertThat(usuario.nome()).isEqualTo("Nome usuário");
		assertThat(usuario.email()).isEqualTo("usuario@email.com");
		assertThat(usuario.nivel()).isEqualTo(Usuario.NIVEL_USUARIO);
		assertThat(usuario.senha()).isEqualTo("senha");
		assertThat(usuario.dataCadastro()).isEqualTo(data);
		assertThat(usuario.dataConfirmacao()).isNull();
		assertThat(usuario.ativo()).isFalse();
	}
	
	// TODO validar se o nome esta vazio ou nulo
	// TODO validar se o email é valido
	// TODO validar se o nivel é um dos niveis permitidos
	// TODO validar se a senha esta vazia ou nula
	// TODO validar se a data de cadastro é nula

	@Test
	public void novoUsuario_dadoNomeVazio_exception(){
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(Usuario.ERR_NOME_INVALIDO);
		
		Date data = new Date();
		
		new Usuario(
				new UsuarioId("USU-ID"),
				"				",
				"usuario@email.com",
				Usuario.NIVEL_USUARIO,
				"senha",
				data,
				null,
				false);
	}

	@Test
	public void novoUsuario_dadoNomeNulo_exception(){
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(Usuario.ERR_NOME_INVALIDO);
		
		Date data = new Date();
		
		new Usuario(
				new UsuarioId("USU-ID"),
				null,
				"usuario@email.com",
				Usuario.NIVEL_USUARIO,
				"senha",
				data,
				null,
				false);
	}

	@Test
	public void novoUsuario_dadoEmailInvalido_exception(){
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(Usuario.ERR_EMAIL_INVALIDO);
		
		Date data = new Date();
		
		new Usuario(
				new UsuarioId("USU-ID"),
				"Nome do usuário",
				"email inválido",
				Usuario.NIVEL_USUARIO,
				"senha",
				data,
				null,
				false);
	}

	@Test
	public void novoUsuario_dadoNivelInexistente_exception(){
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(Usuario.ERR_NIVEL_INVALIDO);
		
		Date data = new Date();
		
		new Usuario(
				new UsuarioId("USU-ID"),
				"Nome do usuário",
				"email@email.com.br",
				"nivel que não existe",
				"senha",
				data,
				null,
				false);
	}
	
	@Test
	public void novoUsuario_dadoSenhaVazia_exception(){
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(Usuario.ERR_SENHA_INVALIDA);
		
		Date data = new Date();
		
		new Usuario(
				new UsuarioId("USU-ID"),
				"Nome do usuário",
				"email@email.com.br",
				Usuario.NIVEL_ADMIN,
				"				",
				data,
				null,
				false);
	}
	
	@Test
	public void novoUsuario_dadoSenhaNula_exception(){
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(Usuario.ERR_SENHA_INVALIDA);
		
		Date data = new Date();
		
		new Usuario(
				new UsuarioId("USU-ID"),
				"Nome do usuário",
				"email@email.com.br",
				Usuario.NIVEL_ADMIN,
				null,
				data,
				null,
				false);
	}
	
	@Test
	public void novoUsuario_dadoDataCadastroNula_exception(){
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(Usuario.ERR_DATA_CADASTRO_INVALIDA);
		
		new Usuario(
				new UsuarioId("USU-ID"),
				"Nome do usuário",
				"email@email.com.br",
				Usuario.NIVEL_ADMIN,
				"senha",
				null,
				null,
				false);
	}
}
