package org.inneo.apisistemas.specs;

import org.inneo.apisistemas.model.Usuario;
import org.springframework.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class UsuarioSpec {
	public static Specification<Usuario> doFiltro(String filter){
		return (root, query, builder) -> {
			Long userID = null;
			if(filter.matches("^\\d+$")) {
				userID = Long.parseLong(filter);
				return builder.equal(root.get("id"), userID);
			}else if(StringUtils.hasText(filter)){
				return builder.like(builder.upper(root.get("username")), "%"+ filter.toUpperCase() +"%");
			}
			return builder.and(new Predicate[0]);			
		};
	}
}
