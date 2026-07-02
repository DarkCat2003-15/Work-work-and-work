package com.example.cajero.service;

import com.example.cajero.entity.Sesion;
import com.example.cajero.entity.User;
import com.example.cajero.repository.SesionRepository;
import com.example.cajero.repository.UserRepository;
import com.example.cajero.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final SesionRepository sesionRepository;

    public AuthService(UserRepository userRepository, SesionRepository sesionRepository) {
        this.userRepository = userRepository;
        this.sesionRepository = sesionRepository;
    }

    public String login(String username, String password) {
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new IllegalArgumentException("Contrasena incorrecta");
        }

        String token = JwtUtil.generarToken(username);

        Sesion sesion = new Sesion();
        sesion.setToken(token);
        sesion.setFechaLogin(LocalDateTime.now());
        sesion.setFechaExpiracion(LocalDateTime.now().plusDays(1));
        sesion.setActiva(true);
        sesion.setUser(usuario);
        sesionRepository.save(sesion);

        return token;
    }
}
