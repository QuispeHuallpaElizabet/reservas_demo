package com.sportify.backend.service;

import com.sportify.backend.model.Rol;
import com.sportify.backend.model.Usuario;
import com.sportify.backend.repository.RolRepository;
import com.sportify.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository; // Para asignar el rol a cada cliente

    AuthService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    // Método para registrar un usuario nuevo si no existe el correo aún
    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        // Validar si el correo existe o no en la base de datos
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(nuevoUsuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("El correo ya está registrado en el sistema.");
        }

        //
        Rol rolCliente = rolRepository.findByNombre("CLIENTE")
        .orElseThrow(() -> new RuntimeException("Error: El rol CLIENTE no existe en la base de datos."));

        // Guardar el usuario
        return usuarioRepository.save(nuevoUsuario);
    };

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