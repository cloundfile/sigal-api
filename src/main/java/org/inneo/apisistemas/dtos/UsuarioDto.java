package org.inneo.apisistemas.dtos;

import org.inneo.apisistemas.enums.Role;
import org.inneo.apisistemas.model.Usuario;

public record UsuarioDto(Long id, String name, String email, String username, boolean alertaEstoque, boolean alertaValidade, boolean alertaEmprestimo, Role role) {
	public UsuarioDto(Usuario usuario){
        this(usuario.getId(),  usuario.getName(), usuario.getEmail(), usuario.getUsername(), usuario.isAlertaEstoque(), usuario.isAlertaValidade(), usuario.isAlertaEmprestimo(), usuario.getRole());
    }
	
	public static UsuarioDto construir(Usuario usuario) {		
		return new UsuarioDto(usuario.getId(),  usuario.getName(), usuario.getEmail(), usuario.getUsername(), usuario.isAlertaEstoque(), usuario.isAlertaValidade(), usuario.isAlertaEmprestimo(), usuario.getRole());
	}
}
