package com.studio.aline.dias.repository;

import com.studio.aline.dias.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // Para verificar se o email já existe
}
