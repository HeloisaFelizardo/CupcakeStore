package com.heloisa.cupcakestore.service;

import com.heloisa.cupcakestore.dto.CupcakeDTO;
import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.repository.CupcakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CupcakeService {

    @Autowired
    private final CupcakeRepository repository;

    private Cupcake toEntity(CupcakeDTO dto) {
        return new Cupcake(
                dto.getId(),
                dto.getNome(),
                dto.getDescricao(),
                dto.getPreco(),
                dto.getEstoque(),
                dto.getImagemUrl()
        );
    }

    private CupcakeDTO toDTO(Cupcake cupcake) {
        return new CupcakeDTO(
                cupcake.getId(),
                cupcake.getNome(),
                cupcake.getDescricao(),
                cupcake.getPreco(),
                cupcake.getEstoque(),
                cupcake.getImagemUrl()
        );
    }

    public CupcakeService(CupcakeRepository repository) {
        this.repository = repository;
    }

    public CupcakeDTO salvar(CupcakeDTO dto) {
        Cupcake cupcake = toEntity(dto);
        Cupcake salvo = repository.save(cupcake);
        return toDTO(salvo);
    }

    public List<CupcakeDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public CupcakeDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Cupcake não encontrado"));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cupcake não encontrado");
        }
        repository.deleteById(id);
    }

    public CupcakeDTO atualizar(Long id, CupcakeDTO dto) {
        Cupcake cupcakeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupcake não encontrado"));

        //Atualiza os campos do cupcake existente
        cupcakeExistente.setNome(dto.getNome());
        cupcakeExistente.setDescricao(dto.getDescricao());
        cupcakeExistente.setPreco(dto.getPreco());
        cupcakeExistente.setEstoque(dto.getEstoque());
        cupcakeExistente.setImagemUrl(dto.getImagemUrl());
        Cupcake atualizado = repository.save(cupcakeExistente);
        return toDTO(atualizado);
    }
}
