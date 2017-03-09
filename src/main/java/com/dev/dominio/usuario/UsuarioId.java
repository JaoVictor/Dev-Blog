package com.dev.dominio.usuario;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
@Access(AccessType.FIELD)
public class UsuarioId {
	
	@Column(name="BANCO_ID")
	private String id;

	public UsuarioId(String id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	private UsuarioId() {}
	
	public String id(){
		return id;
	}
	
	@Override
	public boolean equals(Object objeto) {
		boolean objetosIguais = false;

		if (objeto != null && this.getClass() == objeto.getClass()) {
			UsuarioId objetoTipado = (UsuarioId) objeto;
			objetosIguais = new EqualsBuilder()
				.append(id(), objetoTipado.id())
				.isEquals();
		}

		return objetosIguais;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(99,7)
			.append(id())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return id;
	}

}
