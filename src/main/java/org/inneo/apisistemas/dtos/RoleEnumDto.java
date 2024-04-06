package org.inneo.apisistemas.dtos;

import org.inneo.apisistemas.enums.Role;

import lombok.Data;

@Data
public class RoleEnumDto {
	private String name;
	private Role value;  
	
	public static RoleEnumDto construir(Role role) {
		var roleDTO = new RoleEnumDto();
		roleDTO.setName(role.getName());
		roleDTO.setValue(role);
		return roleDTO;
	}
}
