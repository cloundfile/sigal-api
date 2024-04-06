package org.inneo.apisistemas.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.util.List;

import java.util.Collection;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.inneo.apisistemas.enums.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_usuario")
public class Usuario extends GenericEntity implements UserDetails{	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "alerta_estoque")
	private boolean alertaEstoque;
	
	@Column(name = "alerta_validade")
	private boolean	alertaValidade;
	
	@Column(name = "alerta_emprestimo")
	private boolean	alertaEmprestimo;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}	
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
	  return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
	  return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
	  return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
	  return true;
	}
	
	@Override
	public boolean isEnabled() {
	  return true;
	}	
	
	
}
