package org.inneo.apisistemas.repository;

import java.util.List;
import java.util.Optional;
import org.inneo.apisistemas.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface TokenRep extends JpaRepository<Token, Long>{
	@Query(value = """
			select t from Token t inner join Usuario u 
			on t.usuario.id = u.id 
			where u.id = :id and (t.revogado = false)""")
	List<Token> findAllValidTokenByUsuario(Long id);	

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Token t WHERE t.usuario.id = :id")
	void deleteByUsuario(Long id);
	
	Optional<Token> findByAccessToken(String token);
}
