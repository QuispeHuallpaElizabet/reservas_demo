package com.sportify.backend.service;

import com.sportify.backend.model.Usuario;
import com.sportify.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para registrar un usuario nuevo si no existe el correo aún
    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        // Validar si el correo existe o no en la base de datos
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(nuevoUsuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("El correo ya está registrado en el sistema.");
        }

        // Guardar el usuario
        return usuarioRepository.save(nuevoUsuario);
    }

    // Método para Iniciar Sesión
    public boolean login(String correo, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Compara la contraseña guardada con la ingresada
            return usuario.getPassword().equals(password);
        }
        
        return false; // Si no encuentra el correo
    }
}