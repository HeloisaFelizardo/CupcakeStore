package com.heloisa.cupcakestore.controller;

import com.heloisa.cupcakestore.dto.CupcakeDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
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
    public CupcakeDTO criarCupcake(@RequestBody @Valid CupcakeDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<CupcakeDTO> listarCupcakes() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public CupcakeDTO obterCupcake(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarCupcake(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public CupcakeDTO atualizarCupcake(@PathVariable Long id, @RequestBody @Valid CupcakeDTO dto) {
        return service.atualizar(id, dto);
    }
}
