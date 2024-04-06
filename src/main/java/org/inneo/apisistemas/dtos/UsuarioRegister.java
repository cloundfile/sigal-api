package org.inneo.apisistemas.dtos;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.inneo.apisistemas.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegister {
	private Long id;
	private String name;
	private String email;
	private String username;
	private String password;
	private boolean alertaEstoque;
	private boolean	alertaValidade;
	private boolean	alertaEmprestimo;
	private Role role;
}
