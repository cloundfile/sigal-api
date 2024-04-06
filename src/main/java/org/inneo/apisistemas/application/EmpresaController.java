package org.inneo.apisistemas.application;

import lombok.RequiredArgsConstructor;
import org.inneo.apisistemas.model.Empresa;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import org.inneo.apisistemas.service.EmpresaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/empresa")
@Tag(name = "Empresa", description = "Empresas do grupo.")
public class EmpresaController {
	private final EmpresaService empresaService;
	
	@Operation(summary = "Nova empresa", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@PostMapping
	public ResponseEntity<Empresa> save(@RequestBody Empresa empresa) {
	   return ResponseEntity.ok(empresaService.save(empresa));
	}
	
	@Operation(summary = "Deletar registro", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro deletado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@DeleteMapping("/delete")
	public ResponseEntity<?> pageAllFilter(@RequestParam(required = true) Long id ) {
		try{
			empresaService.delete(id);
			return ResponseEntity.ok("Deletada com sucesso.");
		}catch (Exception e) {
			 return ResponseEntity.badRequest().body("Não foi possivel executar a requisição.");
		}
	  
	}
	
	@Operation(summary = "Buscar empresas", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@GetMapping("/pages")
	public ResponseEntity<?> pageAllFilter(
			@RequestParam(required = false) String filtro,
			@PageableDefault(page = 0, size = 15, sort = "id", direction = Sort.Direction.ASC) Pageable pageable ) {
	   return ResponseEntity.ok(empresaService.pageAllFilter(filtro, pageable));
	}
}
