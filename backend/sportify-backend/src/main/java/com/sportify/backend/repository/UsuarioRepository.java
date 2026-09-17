package com.sportify.backend.repository;

import com.sportify.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

<<<<<<< HEAD
    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
=======
    // Este método busca al usuario por su correo electrónico automáticamente
    Optional<Usuario> findByCorreo(String correo);
>>>>>>> ad6903e743ef7d62da8fa2cfd7c56664d611d4c4
}