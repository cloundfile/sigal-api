package org.inneo.apisistemas.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;
import org.inneo.apisistemas.model.Parametros;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.inneo.apisistemas.dtos.PaginaEnumDto;
import org.inneo.apisistemas.dtos.ParametrosDto;
import org.inneo.apisistemas.dtos.ParametrosRequest;
import org.inneo.apisistemas.enums.PaginaEnum;
import org.inneo.apisistemas.repository.ParametrosRep;

@Service
@RequiredArgsConstructor
public class ParametrosService {
	private final ParametrosRep parametrosRep;
	
	public List<PaginaEnumDto> getPaginas(){
		 return PaginaEnum.findAll();
	}
	
	public ParametrosDto register(ParametrosDto request) {
		Parametros parametros = Parametros.builder()
				.usuarioID(request.usuarioID())
				.pagina(request.pagina())
				.visualizar(request.visualizar())
				.inserir(request.inserir())
				.alterar(request.alterar())
				.excluir(request.excluir())
				.imprimir(request.imprimir())
				.build();
		return new ParametrosDto(parametrosRep.save(parametros));
	}
	
	public String registerAll(List<ParametrosDto> parametros) {
		for(ParametrosDto request: parametros) {
			Parametros parametro = Parametros.builder()
					.usuarioID(request.usuarioID())
					.pagina(request.pagina())
					.visualizar(request.visualizar())
					.inserir(request.inserir())
					.alterar(request.alterar())
					.excluir(request.excluir())
					.imprimir(request.imprimir())
					.build();
			parametrosRep.save(parametro);
		}
		return "Parametros cadastrados com sucesso";
	}
	
	public ParametrosDto daPagina(ParametrosRequest request) {
		Parametros parametros = parametrosRep.findByUsuarioIDAndPagina(request.usuarioID(), request.paginaEnum());
		return new ParametrosDto(parametros);
	}
	
	public Page<ParametrosDto> doUsuario(ParametrosRequest request, Pageable pageable) {
		Page<Parametros> parametros = parametrosRep.findByUsuarioID(request.usuarioID(), pageable);
		List<ParametrosDto> parametrosDto = parametros.getContent().stream().map(ParametrosDto::new).toList();
		return new PageImpl<>(
				parametrosDto,
                pageable,
                parametros.getTotalElements());
	}
}
