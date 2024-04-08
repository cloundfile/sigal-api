package org.inneo.apisistemas.enums;

import java.util.ArrayList;
import java.util.List;

import org.inneo.apisistemas.dtos.PaginaEnumDto;

public enum PaginaEnum {
	USUARIOS("Cadastro de usuários"),
	EMPRESAS("Cadastro de empresas"),
	PERMISSOES("Permissões do usuário");
	
	private final String descricao;
	
	PaginaEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static List<PaginaEnumDto> findAll() {
		List<PaginaEnumDto> paginas = new ArrayList<>();
    	for(PaginaEnum item: PaginaEnum.values()) {
    		paginas.add(PaginaEnumDto.construir(item));
    	}
    	return paginas;
    }
}
