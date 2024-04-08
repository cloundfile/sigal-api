package org.inneo.apisistemas.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;

import jakarta.persistence.Enumerated;
import org.inneo.apisistemas.enums.PaginaEnum;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_parametros")
public class Parametros extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "usuario_id")
	private Long usuarioID;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "pagina")
	private PaginaEnum pagina;
	
	@Column(name = "visualizar")
	private boolean visualizar;
	
	@Column(name = "inserir")
	private boolean inserir;
	
	@Column(name = "alterar")
	private boolean alterar;
	
	@Column(name = "excluir")
	private boolean excluir;
	
	@Column(name = "imprimir")
	private boolean imprimir;
}
