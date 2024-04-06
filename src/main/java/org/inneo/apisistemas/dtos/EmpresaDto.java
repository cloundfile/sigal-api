package org.inneo.apisistemas.dtos;

import org.inneo.apisistemas.model.Empresa;

public record EmpresaDto(Long id, String cnpj, String ie, String razaoSocial, String nomeFantasia, String telefone, String whatsapp, String email, String site, String endereco, String bairro, String cidade, String uf, String cep, Boolean simples, Boolean estoqueZero) {
	public EmpresaDto(Empresa empresa){
        this(empresa.getId(), empresa.getCnpj(), empresa.getIe(), empresa.getRazaoSocial(), empresa.getNomeFantasia(), empresa.getTelefone(), empresa.getWhatsapp(), empresa.getEmail(), empresa.getSite(), empresa.getEndereco(), empresa.getBairro(), empresa.getCidade(), empresa.getUf(), empresa.getCep(), empresa.getSimples(), empresa.getEstoqueZero());
	}
}
