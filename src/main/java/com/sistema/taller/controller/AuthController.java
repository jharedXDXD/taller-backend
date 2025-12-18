package com.sistema.taller.controller;

import com.sistema.taller.entity.Usuario;
import com.sistema.taller.service.JwtService;
import com.sistema.taller.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Usuario usuario = usuarioService.autenticar(
                request.getUsername(),
                request.getPassword()
        );

        String token = jwtService.generarToken(
                usuario.getUsername(),
                usuario.getRol()
        );

        return new LoginResponse(token);
    }

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class LoginResponse {
        private String token;
    }
}
