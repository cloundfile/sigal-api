package org.inneo.apisistemas.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import org.inneo.apisistemas.enums.TokenType;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_token")
public class Token  extends GenericEntity{
	private static final long serialVersionUID = 1L;

	@Column(unique = true, name = "accessToken")
	public String accessToken;

	@Enumerated(EnumType.STRING)
	public TokenType tokenType;

	@Column(name = "renovado")
	private boolean revogado;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario usuario;

}
