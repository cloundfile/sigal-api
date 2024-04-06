package org.inneo.apisistemas.service;

import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.inneo.apisistemas.enums.Role;
import org.inneo.apisistemas.model.Token;

import org.inneo.apisistemas.model.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import org.inneo.apisistemas.dtos.UsuarioDto;
import org.inneo.apisistemas.enums.TokenType;
import org.inneo.apisistemas.dtos.RoleEnumDto;
import org.springframework.stereotype.Service;

import org.inneo.apisistemas.config.JwtService;
import org.springframework.data.domain.PageImpl;
import org.inneo.apisistemas.dtos.TokenResponse;
import org.springframework.data.domain.Pageable;
import org.inneo.apisistemas.dtos.UsuarioRequest;

import org.inneo.apisistemas.repository.TokenRep;
import org.inneo.apisistemas.dtos.UsuarioRegister;
import org.inneo.apisistemas.repository.UsuarioRep;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	private final TokenRep tokenRep;
	private final UsuarioRep usuarioRep;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	public String register(UsuarioRegister register) {
		register = validate(register);		
		Usuario usuario = new Usuario();
		
		if(register.getId() != null) {
			usuario = usuarioRep.findByUsername(register.getUsername()).get();
			BeanUtils.copyProperties(register, usuario);							
		} else {
			usuario = Usuario.builder()
					.name(register.getName())
					.email(register.getEmail())
					.username(register.getUsername())
					.password(register.getPassword())
					.role(register.getRole())
					.build();
		}		
		
	    var created = usuarioRep.save(usuario);	    
	    return created.getId() + " Usuário cadastrado com sucesso!";
	  }
	
	public String deletarRegistro(Long id) {			
		tokenRep.deleteByUsuario(id);	
		usuarioRep.delete(usuarioRep.getReferenceById(id));
		return "Registro deletado com sucesso.";
	}
	
	private UsuarioRegister validate(UsuarioRegister register) {
		if(register.getId() == null && register.getPassword() == null) {
			throw new NullPointerException("Senha obrigatorio para novos usuários.");
		}
		
		if(register.getId() == null && register.getPassword() != null) {
			register.setPassword(passwordEncoder.encode(register.getPassword()));
		} 
		
		if(register.getId() != null && register.getPassword() != null) {
			register.setPassword(passwordEncoder.encode(register.getPassword()));
		} 		
		
		if(register.getId() != null && register.getPassword() == null) {
			String currentPassword = usuarioRep.findByUsername(register.getUsername()).get().getPassword();
			register.setPassword(currentPassword);
		}		
		
		if(register.getId() == null && register.getRole() == null) {
			register.setRole(Role.ROLE_VENDEDOR);
		}
		
		if(register.getId() != null && register.getRole() == null) {
			Role role = usuarioRep.findByUsername(register.getUsername()).get().getRole();
			register.setRole(role);
		}
		
		return register;
	}

	public TokenResponse authenticate(UsuarioRequest request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getUsername(),
	            request.getPassword()
	        )
	    );
	    var usuario = usuarioRep.findByUsername(request.getUsername()).orElseThrow();
	    var jwtToken = jwtService.generateToken(usuario);
	    
	    revokeAllUsuarioTokens(usuario);
	    registerToken(usuario, jwtToken);
	    
	    return TokenResponse.builder()
	        .accessToken(jwtToken)
	        .build();
	}
	
	private void registerToken(Usuario usuario, String jwtToken) {
	    var token = Token.builder()
	        .usuario(usuario)
	        .accessToken(jwtToken)
	        .tokenType(TokenType.BEARER)
	        .revogado(false)        
	        .build();
	    tokenRep.save(token);
  }

	private void revokeAllUsuarioTokens(Usuario usuario) {
		var validUserTokens = tokenRep.findAllValidTokenByUsuario(usuario.getId());
		if (validUserTokens.isEmpty()) return;
    
		validUserTokens.forEach(token -> {
			token.setRevogado(true);
			token.setDisableIn(new Date());
		});
		tokenRep.saveAll(validUserTokens);
	}
	  
	public UsuarioDto findByUsername(String username) {
		  return UsuarioDto.construir(usuarioRep.findByUsername(username).get());
	}
	
	public Page<UsuarioDto> pageAll(Pageable pageable) {
		Page<Usuario> usuarios = usuarioRep.findAll(pageable);
		List<UsuarioDto> usuariosDto = usuarios.getContent().stream().map(UsuarioDto::new).toList();
		return new PageImpl<>(
				usuariosDto,
                pageable,
                usuarios.getTotalElements());
	}	
	
	public List<RoleEnumDto> roles(){
		 return Role.roles();
	}
}
