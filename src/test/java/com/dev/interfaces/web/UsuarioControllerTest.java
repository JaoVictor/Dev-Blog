package com.dev.interfaces.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.dev.aplicacao.usuario.NovoUsuarioComando;
import com.dev.aplicacao.usuario.NovoUsuarioServicoAplicacao;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=UsuarioController.class, secure=false)
//@SpringBootTest
//@AutoConfigureMockMvc(secure = false)
public class UsuarioControllerTest {

	@MockBean
	NovoUsuarioServicoAplicacao novoUsuarioServicoAplicacao;
	
	@Autowired
	private MockMvc mvc;

//	@Before
//	public void setup(){
//	    MockitoAnnotations.initMocks(this);
//	}
	
	@Test
	public void novoUsuario_DadoPayload_novoUsuarioServicoAplicacaoAcionado() throws Exception{
		String payload = "{"+
				"\"nome\": \"nome do usuario\","+
				"\"email\": \"email@email.com.br\","+
				"\"senha\": \"senha do usuario\"}";
		
		when(novoUsuarioServicoAplicacao.novoUsuario(any()))
			.thenReturn("USUARIO-ID");
		
		this.mvc.perform(post("/usuarios").contentType(APPLICATION_JSON)
				.content(payload))
			.andExpect(status().isCreated())
			.andExpect(redirectedUrlPattern("http://*/usuario/USUARIO-ID"));
		
		ArgumentCaptor<NovoUsuarioComando> argument = ArgumentCaptor.forClass(NovoUsuarioComando.class);
		verify(novoUsuarioServicoAplicacao).novoUsuario(argument.capture());
		NovoUsuarioComando comando = argument.getValue();
		
		assertThat(comando.getNome()).isEqualTo("nome do usuario");
		assertThat(comando.getEmail()).isEqualTo("email@email.com.br");
		assertThat(comando.getSenha()).isEqualTo("senha do usuario");
	}
}
