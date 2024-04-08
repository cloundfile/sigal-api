package org.inneo.apisistemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.inneo.apisistemas.enums.PaginaEnum;
import org.inneo.apisistemas.model.Parametros;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ParametrosRep extends JpaRepository<Parametros, Long>, JpaSpecificationExecutor<Parametros>{
	Parametros findByUsuarioIDAndPagina(Long usuarioID, PaginaEnum pagina);
	Page<Parametros> findByUsuarioID(Long usuarioID, Pageable pageable);
}
