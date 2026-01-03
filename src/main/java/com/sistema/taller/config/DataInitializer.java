package com.sistema.taller.config;

import com.sistema.taller.entity.Usuario;
import com.sistema.taller.entity.Rol;
import com.sistema.taller.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Crear usuario administrador solo si no existe ninguno
        if (usuarioRepository.count() == 0) {

            Usuario admin = new Usuario();
            admin.setNombre("Administrador");               // ✅ CORRECCIÓN CLAVE
            admin.setEmail("admin@taller.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ADMIN);
            admin.setActivo(true);

            usuarioRepository.save(admin);
        }
    }
}
