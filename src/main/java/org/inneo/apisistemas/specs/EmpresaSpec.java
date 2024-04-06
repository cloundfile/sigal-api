package org.inneo.apisistemas.specs;

import org.inneo.apisistemas.model.Empresa;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class EmpresaSpec {
	public static Specification<Empresa> doFiltro(String filtro){
		return (root, query, builder) -> {
			if(StringUtils.hasText(filtro)) {
				Predicate predicateFieldNomeFantasia 	= builder.like(builder.upper(root.get("razaoSocial")), "%"+ filtro.toUpperCase() +"%");
				Predicate predicateFieldRazaoSocial 	= builder.like(builder.upper(root.get("nomeFantasia")), "%"+ filtro.toUpperCase() +"%");
				return builder.or(predicateFieldNomeFantasia, predicateFieldRazaoSocial);
			}
			return builder.and(new Predicate[0]);			
		};
	}
	
	public static Specification<Empresa> daRazaoSocial(String razaoSocial){
        return (root, query, builder) -> {
            if(StringUtils.hasText(razaoSocial)) {
                return builder.like(builder.upper(root.get("razaoSocial")), "%" + razaoSocial.toUpperCase() + "%"
                );
            }
            return builder.and(new Predicate[0]);
        };
    }
	
	
	public static Specification<Empresa> doNomeFantasia(String nomeFantasia){
        return (root, query, builder) -> {
            if(StringUtils.hasText(nomeFantasia)) {
                return builder.like(builder.upper(root.get("nomeFantasia")), "%" + nomeFantasia.toUpperCase() + "%");
            }
            return builder.and(new Predicate[0]);
        };
    }
}
