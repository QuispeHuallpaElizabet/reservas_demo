package com.sportify.backend.repository;

import com.sportify.backend.model.UsuarioRol;
import com.sportify.backend.model.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

    List<UsuarioRol> findByIdUsuario(Long idUsuario);

    List<UsuarioRol> findByIdRol(Long idRol);
}