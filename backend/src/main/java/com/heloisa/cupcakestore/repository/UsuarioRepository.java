package com.heloisa.cupcakestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heloisa.cupcakestore.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}