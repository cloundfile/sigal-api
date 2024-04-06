package org.inneo.apisistemas.repository;

import org.inneo.apisistemas.model.Empresa;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface EmpresaRep extends JpaRepository<Empresa, Long>, JpaSpecificationExecutor<Empresa>{
	Empresa findByRazaoSocial(String razaoSocial);
	Empresa findByNomeFantasia(String nomeFantasia);

}
