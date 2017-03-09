package com.dev.dominio.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages="com.dev.interfaces.repositorio")
public class UsuarioRepositorioTest {

	@Autowired
	TestEntityManager em;
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	Usuario usuario;
	
	@Before
	public void setup(){
		
	usuario = new Usuario(
				new UsuarioId("USUARIO-ID"), 
				"Nome",
				"email@email.com.br", 
				Usuario.NIVEL_ADMIN, 
				"senha",
				new Date(), 
				null, 
				false);
	}
	
	@Test
	public void salvarUsuario(){
	
		usuarioRepositorio.salvar(usuario);
		
		Usuario usuarioEncontrado = em.find(Usuario.class, em.getId(usuario));

		assertThat(usuarioEncontrado.usuarioId()).isEqualTo(new UsuarioId("USUARIO-ID"));
		assertThat(usuarioEncontrado.nome()).isEqualTo("Nome");
		assertThat(usuarioEncontrado.email()).isEqualTo("email@email.com.br");
		assertThat(usuarioEncontrado.nivel()).isEqualTo(Usuario.NIVEL_ADMIN);
		assertThat(usuarioEncontrado.senha()).isEqualTo("senha");
		assertThat(usuarioEncontrado.dataCadastro()).isEqualTo(usuario.dataCadastro());
		assertThat(usuarioEncontrado.dataConfirmacao()).isNull();
		assertThat(usuarioEncontrado.ativo()).isFalse();
	}
	
	@Test
	public void obterUsuarioPeloId_DadoUsuarioId_retornaUsuario(){
	
		em.persist(usuario);
		
		Usuario usuarioEncontrado = usuarioRepositorio.obterPeloId(new UsuarioId("USUARIO-ID")).get();

		assertThat(usuarioEncontrado.usuarioId()).isEqualTo(new UsuarioId("USUARIO-ID"));
		assertThat(usuarioEncontrado.nome()).isEqualTo("Nome");
		assertThat(usuarioEncontrado.email()).isEqualTo("email@email.com.br");
		assertThat(usuarioEncontrado.nivel()).isEqualTo(Usuario.NIVEL_ADMIN);
		assertThat(usuarioEncontrado.senha()).isEqualTo("senha");
		assertThat(usuarioEncontrado.dataCadastro()).isEqualTo(usuario.dataCadastro());
		assertThat(usuarioEncontrado.dataConfirmacao()).isNull();
		assertThat(usuarioEncontrado.ativo()).isFalse();
	}
	
	@Test
	public void obterPeloEmail_DadoEmail_RetornaUsuario(){
		
		em.persist(usuario);
		
		Usuario usuarioEncontrado = usuarioRepositorio.obterPeloEmail(usuario.email()).get();
		
		assertThat(usuarioEncontrado.usuarioId()).isEqualTo(new UsuarioId("USUARIO-ID"));
	}
	
	@Test
	public void obterTodosOsUsuarios(){
		
		em.persist(usuario);
		
		List<Usuario> usuariosEncontrados = usuarioRepositorio.obterTodos();
		
		assertThat(usuariosEncontrados.size()).isEqualTo(1);
		assertThat(usuariosEncontrados.get(0).usuarioId()).isEqualTo(new UsuarioId("USUARIO-ID"));
	}
}
