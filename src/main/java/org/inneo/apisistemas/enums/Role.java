package org.inneo.apisistemas.enums;

import java.util.ArrayList;
import java.util.List;

import org.inneo.apisistemas.dtos.RoleEnumDto;

public enum Role {
	ROLE_ADMIN ("Gerente"),
	ROLE_VENDEDOR ("Vendedor");
	
	private String name;

	Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public static List<RoleEnumDto> roles() {
		List<RoleEnumDto> roles = new ArrayList<>();
    	for(Role item: Role.values()) {
    		roles.add(RoleEnumDto.construir(item));
    	}
    	return roles;
    }
}
