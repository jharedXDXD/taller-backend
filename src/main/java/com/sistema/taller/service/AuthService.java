package com.sistema.taller.service;

import com.sistema.taller.entity.AuthRequest;
import com.sistema.taller.entity.AuthResponse;
import com.sistema.taller.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var usuario = usuarioRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(usuario);

        return new AuthResponse(token);
    }
}

