package org.inneo.apisistemas.dtos;

import org.inneo.apisistemas.enums.PaginaEnum;

public record ParametrosRequest(Long usuarioID, PaginaEnum paginaEnum) {

}
