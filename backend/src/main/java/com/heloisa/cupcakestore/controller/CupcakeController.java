package com.heloisa.cupcakestore.controller;

import org.springframework.web.bind.annotation.*;
import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.service.CupcakeService;
import java.util.List;

@RestController
@RequestMapping("/cupcakes")
public class CupcakeController {

    private final CupcakeService service;

    public CupcakeController(CupcakeService service) {
        this.service = service;
    }

    @PostMapping
    public Cupcake criarCupcake(@RequestBody Cupcake cupcake) {
        return service.salvar(cupcake);
    }

    @GetMapping
    public List<Cupcake> listarCupcakes() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Cupcake obterCupcake(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

}
