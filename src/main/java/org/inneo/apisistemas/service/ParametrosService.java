package org.inneo.apisistemas.service;

import lombok.RequiredArgsConstructor;
import org.inneo.apisistemas.model.Usuario;
import org.springframework.stereotype.Service;

import org.inneo.apisistemas.model.Parametros;
import org.inneo.apisistemas.dtos.ParametrosDto;
import org.inneo.apisistemas.repository.UsuarioRep;
import org.inneo.apisistemas.repository.ParametrosRep;

@Service
@RequiredArgsConstructor
public class ParametrosService {
	private final ParametrosRep parametrosRep;
	private final UsuarioRep usuarioRep;
	
	public ParametrosDto register(ParametrosDto request) {
		Usuario usuario = usuarioRep.getReferenceById(request.usuario());
		Parametros parametros = Parametros.builder()
				.usuario(usuario)
				.pagina(request.pagina())
				.visualizar(request.visualizar())
				.inserir(request.inserir())
				.alterar(request.alterar())
				.excluir(request.excluir())
				.imprimir(request.imprimir())
				.build();
		return new ParametrosDto(parametrosRep.save(parametros));
	}
}
