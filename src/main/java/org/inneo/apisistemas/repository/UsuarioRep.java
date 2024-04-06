package org.inneo.apisistemas.repository;

import java.util.Optional;

import org.inneo.apisistemas.model.Usuario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
	 Optional<Usuario> findByUsername(String username);
}
