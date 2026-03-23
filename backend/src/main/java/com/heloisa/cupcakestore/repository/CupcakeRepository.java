package com.heloisa.cupcakestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.heloisa.cupcakestore.model.Cupcake;

public interface CupcakeRepository extends JpaRepository<Cupcake, Long> {

}
