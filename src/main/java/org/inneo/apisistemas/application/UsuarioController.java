package org.inneo.apisistemas.application;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.inneo.apisistemas.dtos.UsuarioDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.inneo.apisistemas.dtos.TokenResponse;

import org.inneo.apisistemas.dtos.UsuarioRequest;
import org.inneo.apisistemas.dtos.UsuarioRegister;
import org.springframework.data.web.PageableDefault;
import org.inneo.apisistemas.service.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@Tag(name = "Usuários")
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	@Operation(summary = "Logar usuário", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Usuário não autorizado!" )
	})
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> authenticate(@RequestBody  @Valid UsuarioRequest request) {
	   return ResponseEntity.ok(usuarioService.authenticate(request));
	}	
	
	@Operation(summary = "Cadastrar novo usuário", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody  @Valid UsuarioRegister register) {
	   return ResponseEntity.ok(usuarioService.register(register));
	}
	
	@Operation(summary = "Deletar registro", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro deletado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@DeleteMapping("/delete")
	public ResponseEntity<?> deletarRegistro(@RequestParam(required = true) Long id ) {
		return ResponseEntity.ok(usuarioService.deletarRegistro(id));		
	}
	
	@Operation(summary = "Buscar por username", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Localizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	
	@GetMapping("/filter")
	public ResponseEntity<?> findByUsername(@RequestParam(required = true) String username){
		UsuarioDto usuario = usuarioService.findByUsername(username);
		return ResponseEntity.ok(usuario);
	}
	
	@Operation(summary = "Listar todos usuários", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@GetMapping("/pages")
	public ResponseEntity<?> pageAll(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){		
		return ResponseEntity.ok(usuarioService.pageAll(pageable));
	}
	
	@Operation(summary = "Listar todas permições", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição de cadastro gerou um erro." ),
			@ApiResponse(responseCode = "401", description = "Acesso não autorizado!" )
	})
	@GetMapping("/roles")
	public ResponseEntity<?> getRoles(){
		return ResponseEntity.ok(usuarioService.roles());
	}
}
