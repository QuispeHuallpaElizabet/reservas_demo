package com.sportify.backend.service;

import com.sportify.backend.model.Rol;
import com.sportify.backend.model.Usuario;
import com.sportify.backend.model.UsuarioRol;
import com.sportify.backend.repository.RolRepository;
import com.sportify.backend.repository.UsuarioRepository;
import com.sportify.backend.repository.UsuarioRolRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final RolRepository rolRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final UsuarioRepository usuarioRepository;

    public AuthService(
        UsuarioRepository usuarioRepository,
        RolRepository rolRepository,
        UsuarioRolRepository usuarioRolRepository
) {
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
    this.usuarioRolRepository = usuarioRolRepository;
}

    // Método para registrar un usuario nuevo si no existe el correo aún
    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        // Validar si el correo existe o no en la base de datos
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(nuevoUsuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("El correo ya está registrado en el sistema.");
        }

        // Guardar el usuario
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        

        //buscar rol="CLIENTE"
        Optional<Rol> rolCliente =
                rolRepository.findByNombre("CLIENTE");

        if (rolCliente.isEmpty()) {
            throw new RuntimeException(
                    "El rol CLIENTE no existe en la base de datos."
            );
        }

        //crear la relacion usuario-rol
        UsuarioRol usuarioRol = new UsuarioRol(
                usuarioGuardado.getIdUsuario(),
                rolCliente.get().getIdRol()
        );

        // Guardar la relación
        usuarioRolRepository.save(usuarioRol);

        return usuarioGuardado;
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