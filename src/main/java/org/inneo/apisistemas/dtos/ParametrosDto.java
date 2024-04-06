package org.inneo.apisistemas.dtos;

import org.inneo.apisistemas.enums.PaginaEnum;
import org.inneo.apisistemas.model.Parametros;
public record ParametrosDto(Long id, Long usuario, PaginaEnum pagina, boolean visualizar, boolean inserir, boolean alterar, boolean excluir, boolean imprimir) {
	
	public ParametrosDto(Parametros parametros){
        this(parametros.getId(), parametros.usuario.getId(), parametros.getPagina(), parametros.isVisualizar(), parametros.isInserir(), parametros.isAlterar(), parametros.isExcluir(), parametros.isImprimir());
	}
}
