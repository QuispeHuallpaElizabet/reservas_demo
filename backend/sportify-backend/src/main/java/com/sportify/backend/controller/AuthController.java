package com.sportify.backend.controller;

import com.sportify.backend.model.Usuario;
import com.sportify.backend.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
    origins = "http://localhost:5173",
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
    }
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioRegistrado = authService.registrarUsuario(usuario);

            return ResponseEntity.ok("¡Usuario registrado con éxito! ID asignado: "+ usuarioRegistrado.getIdUsuario());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuarioLogin) {

        boolean autenticado =authService.login(usuarioLogin.getCorreo(),usuarioLogin.getPassword());

        if (autenticado) {
            return ResponseEntity.ok("¡Inicio de sesión exitoso!");
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas "+ "(correo o contraseña inválidos)");
    }
}