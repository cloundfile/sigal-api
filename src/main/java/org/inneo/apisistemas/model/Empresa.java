package org.inneo.apisistemas.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.validation.constraints.NotBlank;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_empresa")
public class Empresa extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name = "cnpj", unique = true)
	private String cnpj;
	
	@NotBlank
	@Column(name = "ie", unique = true)
	private String ie;
	
	@NotBlank
	@Column(name = "razao_social")
	private String razaoSocial;
	
	@NotBlank
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	@NotBlank
	@Column(name = "telefone")
	private String telefone;
	
	@NotBlank
	@Column(name = "whatsapp")
	private String whatsapp;
	
	@NotBlank
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Column(name = "site")
	private String site;
	
	@NotBlank
	@Column(name = "endereco")
	private String endereco;
	
	@NotBlank
	@Column(name = "bairro")
	private String bairro;
	
	@NotBlank
	@Column(name = "cidade")
	private String cidade;

	@NotBlank
	@Column(name = "uf")
	private String uf;
	
	@NotBlank
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "simples")
	private Boolean simples;
	
	@Column(name = "estoque_zero")
	private Boolean estoqueZero;

}
