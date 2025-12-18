package com.sistema.taller.config;

import com.sistema.taller.entity.Usuario;
import com.sistema.taller.repository.UsuarioRepository;
import com.sistema.taller.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 🔴 NO FILTRAR LOGIN
    	if (request.getServletPath().startsWith("/auth")) {
    	    filterChain.doFilter(request, response);
    	    return;
    	}


        final String authHeader = request.getHeader("Authorization");

        // 🔴 SI NO HAY TOKEN, SEGUIMOS SIN AUTENTICAR
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String username = jwtService.extraerUsername(token);

            if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

                Usuario usuario = usuarioRepository
                        .findByUsername(username)
                        .orElse(null);

                if (usuario != null) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    usuario.getUsername(),
                                    null,
                                    List.of(new SimpleGrantedAuthority(
                                            "ROLE_" + usuario.getRol()))
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                }
            }

        } catch (Exception e) {
            // ❌ TOKEN MALFORMADO → NO TIRAMOS 500
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}

