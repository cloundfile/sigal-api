package org.inneo.apisistemas.config;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.ServletException;
import org.springframework.lang.NonNull;
import org.springframework.http.MediaType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.inneo.apisistemas.repository.TokenRep;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{
	private final TokenRep tokenRep;
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws ServletException, IOException {
    	final String authHeader = request.getHeader("Authorization");
    	final String jwt;
    	final String username;
    	
    	if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
    		filterChain.doFilter(request, response);
    		return;
    	}
    	
    	try {
    		jwt = authHeader.substring(7);	
    		username = jwtService.extractUsername(jwt); 	 
    		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);		 
    			var isTokenValid = tokenRep.findByAccessToken(jwt).map(tk -> !tk.isRevogado()).orElse(false);
	
    			if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
    				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
	        
    				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));				 
    				SecurityContextHolder.getContext().setAuthentication(authToken);				 
    			}
    		}	 
		 	filterChain.doFilter(request, response);
		 	
    	}catch (Exception e) {	    		  
    		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    		Map<String, String> unauthorized = new HashMap<>();
    		unauthorized.put("unauthorized", "Token inválido ou expirado!");
    		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    		new ObjectMapper().writeValue(response.getOutputStream(), unauthorized);    		
    	}		 	
	}	
}
