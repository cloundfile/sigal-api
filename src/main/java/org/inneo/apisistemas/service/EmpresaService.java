package org.inneo.apisistemas.service;

import java.util.List;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.inneo.apisistemas.model.Empresa;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.inneo.apisistemas.dtos.EmpresaDto;

import org.springframework.stereotype.Service;
import org.inneo.apisistemas.specs.EmpresaSpec;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.inneo.apisistemas.repository.EmpresaRep;


@Service
@AllArgsConstructor
public class EmpresaService {
	private final EmpresaRep empresaRep;
	
	public Empresa save(@Valid Empresa empresa) {
		Empresa response = empresa.getId() != null? empresaRep.getReferenceById(empresa.getId()) : new Empresa();
		empresa.setCreatedIn(response.getCreatedIn());
		BeanUtils.copyProperties(empresa, response);
		return empresaRep.save(empresa);
	}
	
	public Page<EmpresaDto> pageAllFilter(String filtro, Pageable pageable) {
		Page<Empresa> empresas = empresaRep.findAll(EmpresaSpec.doFiltro(filtro),pageable);
		List<EmpresaDto> empresasDto = empresas.getContent().stream().map(EmpresaDto::new).toList();
		return new PageImpl<>(
				empresasDto,
                pageable,
                empresas.getTotalElements());
	}
	
	public void delete(Long id) {
		Empresa empresa = empresaRep.getReferenceById(id);
		empresaRep.delete(empresa);
	}
}
