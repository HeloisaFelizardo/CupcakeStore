package com.heloisa.cupcakestore.repository;

import com.heloisa.cupcakestore.model.Cupcake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;

import java.util.Optional;

public interface CupcakeRepository extends JpaRepository<Cupcake, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Cupcake c WHERE c.id = :id")
    Optional<Cupcake> buscarPorIdComLock(Long id);
}