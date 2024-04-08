package org.inneo.apisistemas.application;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.inneo.apisistemas.dtos.ParametrosDto;
import org.inneo.apisistemas.dtos.ParametrosRequest;
import org.inneo.apisistemas.service.ParametrosService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@Operation(summary = "Paginas sistema", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Requisição executada com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Usuário não autorizado!" )
	})
	@GetMapping("/paginas")
	public ResponseEntity<?> findAll() {
	   return ResponseEntity.ok(parametrosService.getPaginas());
	}	
	
	
	@Operation(summary = "Parametros", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Usuário não autorizado!" )
	})
	@PostMapping
	public ResponseEntity<?> register(@RequestBody  @Valid ParametrosDto parametros) {
	   return ResponseEntity.ok(parametrosService.register(parametros));
	}
	
	@Operation(summary = "Parametros", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Usuário não autorizado!" )
	})
	@PostMapping("registerAll")
	public ResponseEntity<?> registerAll(@RequestBody  @Valid List<ParametrosDto> parametros) {
	   return ResponseEntity.ok(parametrosService.registerAll(parametros));
	}
	
	
	@Operation(summary = "Retorna um registro", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Parâmetro encontrado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@GetMapping("/daPagina")
	public ResponseEntity<?> daPagina(
			@RequestBody ParametrosRequest request){		
		return ResponseEntity.ok(parametrosService.daPagina(request));
	}
	
	@Operation(summary = "Paginar parâmetros", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@PostMapping("/doUsuario")
	public ResponseEntity<?> pageAll(
			@RequestBody ParametrosRequest parametrosRequest,
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){		
		return ResponseEntity.ok(parametrosService.doUsuario(parametrosRequest, pageable));
	}
	
	
}
