package com.dev.dominio.usuario;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;

@Entity
@SequenceGenerator(name="SEQ", sequenceName="SQ_USUARIOS")
@Table(name="USUARIOS")
public class Usuario {

	@Embedded
	private UsuarioId usuarioId;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "NIVEL")
	private String nivel;
	
	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;
	
	@Column(name = "DATA_CONFIRMACAO")
	private Date dataConfirmacao;
	
	@Column(name = "ATIVO")
	private boolean ativo;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ")
	@Column(name="ID")
	protected Long id;
	
	public static final String NIVEL_USUARIO = "nivel-usuario";
	public static final String NIVEL_ADMIN = "nivel-admin";
	
	public static final String ERR_NOME_INVALIDO = "Nome do usuário inválido";
	public static final String ERR_EMAIL_INVALIDO = "Email inválido";
	public static final String ERR_NIVEL_INVALIDO = "Nivel inválido";
	public static final String ERR_SENHA_INVALIDA = "Senha inválida";
	public static final String ERR_DATA_CADASTRO_INVALIDA = "Data de cadastro inválida";

	public Usuario(
			UsuarioId usuarioId, 
			String nome, 
			String email, 
			String nivel, 
			String senha, 
			Date dataCadastro,
			Date dataConfirmacao,
			boolean ativo) {
		
		setUsuarioId(usuarioId);
		setNome(nome);
		setEmail(email);
		setNivel(nivel);
		setSenha(senha);
		setDataCadastro(dataCadastro);
		setDataConfirmacao(dataConfirmacao);
		setAtivo(ativo);
	}

	private void setUsuarioId(UsuarioId usuarioId) {
		this.usuarioId = usuarioId;
	}

	private void setNome(String nome) {
		
		if(nome == null || StringUtils.isEmpty(nome.trim()))
			throw new IllegalArgumentException(ERR_NOME_INVALIDO);
		
		this.nome = nome;
	}

	private void setEmail(String email) {
		
		if(!EmailValidator.getInstance().isValid(email))
			throw new IllegalArgumentException(ERR_EMAIL_INVALIDO);
			
		this.email = email;
	}

	private void setSenha(String senha) {
		
		if(senha == null || StringUtils.isEmpty(senha.trim()))
			throw new IllegalArgumentException(ERR_SENHA_INVALIDA);
		
		this.senha = senha;
	}

	private void setNivel(String nivel) {
		
		if(!nivel.equals(NIVEL_ADMIN) && !nivel.equals(NIVEL_USUARIO))
			throw new IllegalArgumentException(ERR_NIVEL_INVALIDO); 
		
		this.nivel = nivel;
	}

	private void setDataCadastro(Date dataCadastro) {
		
		if(dataCadastro == null)
			throw new IllegalArgumentException(ERR_DATA_CADASTRO_INVALIDA);
		
		this.dataCadastro = dataCadastro;
	}

	private void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}
	
	private void setAtivo(boolean ativo){
		this.ativo = ativo;
	}

	public UsuarioId usuarioId() {
		return usuarioId;
	}

	public String nome() {
		return nome;
	}

	public String email() {
		return email;
	}

	public String senha() {
		return senha;
	}

	public String nivel() {
		return nivel;
	}

	public Date dataCadastro() {
		return dataCadastro;
	}

	public Date dataConfirmacao() {
		return dataConfirmacao;
	}

	public boolean ativo() {
		return ativo;
	}
}
