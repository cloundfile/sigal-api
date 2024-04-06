package org.inneo.apisistemas.repository;

import org.inneo.apisistemas.model.Parametros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametrosRep extends JpaRepository<Parametros, Long>, JpaSpecificationExecutor<Parametros>{

}
