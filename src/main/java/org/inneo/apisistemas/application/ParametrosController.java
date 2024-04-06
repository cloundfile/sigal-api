package org.inneo.apisistemas.application;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.inneo.apisistemas.dtos.ParametrosDto;

import org.inneo.apisistemas.service.ParametrosService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Parametros")
@RequestMapping("/api/v1/parametros")
public class ParametrosController {
	private final ParametrosService parametrosService;
	
	@Operation(summary = "Parametros", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Usuário não autorizado!" )
	})
	@PostMapping
	public ResponseEntity<ParametrosDto> authenticate(@RequestBody  @Valid ParametrosDto parametros) {
	   return ResponseEntity.ok(parametrosService.register(parametros));
	}	
}
