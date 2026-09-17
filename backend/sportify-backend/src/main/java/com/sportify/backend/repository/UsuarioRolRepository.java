package com.sportify.backend.repository;

import com.sportify.backend.model.UsuarioRol;
import com.sportify.backend.model.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

    List<UsuarioRol> findByIdUsuario(Long idUsuario);

    List<UsuarioRol> findByIdRol(Long idRol);
}