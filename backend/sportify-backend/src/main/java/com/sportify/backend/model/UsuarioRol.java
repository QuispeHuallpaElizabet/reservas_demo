package com.sportify.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_rol")
@IdClass(UsuarioRolId.class)
public class UsuarioRol {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Id
    @Column(name = "id_rol")
    private Long idRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    public UsuarioRol() {
    }

    public UsuarioRol(Long idUsuario, Long idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}