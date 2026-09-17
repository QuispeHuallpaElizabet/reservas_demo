package com.sportify.backend.controller;

import com.sportify.backend.model.Usuario;
import com.sportify.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint para registrar usuarios
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioRegistrado = authService.registrarUsuario(usuario);
            return ResponseEntity.ok("¡Usuario registrado con éxito! ID asignado: " + usuarioRegistrado.getIdUsuario());
        } catch (RuntimeException e) {
            // Devuelve error si el correo ya está registrado
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint de Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuarioLogin) {
        boolean autenticado = authService.login(usuarioLogin.getCorreo(), usuarioLogin.getPassword());
        
        if (autenticado) {
            return ResponseEntity.ok("¡Inicio de sesión exitoso!");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas (correo o contraseña inválidos)");
        }
    }
}   