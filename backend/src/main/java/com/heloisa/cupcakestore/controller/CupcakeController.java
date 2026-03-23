package com.heloisa.cupcakestore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heloisa.cupcakestore.repository.CupcakeRepository;
import com.heloisa.cupcakestore.model.Cupcake;
import java.util.List;

@RestController
@RequestMapping("/cupcakes")
public class CupcakeController {

    private final CupcakeRepository repository;

    public CupcakeController(CupcakeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Cupcake criarCupcake(@RequestBody Cupcake cupcake) {
        return repository.save(cupcake);
    }

    @GetMapping
    public List<Cupcake> listarCupcakes() {
        return repository.findAll();
    }

}
