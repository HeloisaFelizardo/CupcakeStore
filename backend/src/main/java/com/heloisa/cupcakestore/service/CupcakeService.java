package com.heloisa.cupcakestore.service;

import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.repository.CupcakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CupcakeService {

    @Autowired
    private final CupcakeRepository repository;

    public CupcakeService(CupcakeRepository repository) {
        this.repository = repository;
    }

    public Cupcake salvar(Cupcake cupcake) {
        return repository.save(cupcake);
    }

    public List<Cupcake> listarTodos() {
        return repository.findAll();
    }

    public Cupcake buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupcake não encontrado"));
    }

}
