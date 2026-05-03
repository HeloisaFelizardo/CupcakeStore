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
    private final CupcakeRepository cupcakeRepository;

    private Cupcake toEntity(CupcakeDTO dto) {
        return new Cupcake(
                dto.getId(),
                dto.getNome(),
                dto.getDescricao(),
                dto.getPreco(),
                dto.getEstoque(),
                dto.getImagemUrl());
    }

    private CupcakeDTO toDTO(Cupcake cupcake) {
        return new CupcakeDTO(
                cupcake.getId(),
                cupcake.getNome(),
                cupcake.getDescricao(),
                cupcake.getPreco(),
                cupcake.getEstoque(),
                cupcake.getImagemUrl());
    }

    public CupcakeService(CupcakeRepository cupcakeRepository) {
        this.cupcakeRepository = cupcakeRepository;
    }

    public CupcakeDTO salvar(CupcakeDTO dto) {
        Cupcake cupcake = toEntity(dto);
        Cupcake salvo = cupcakeRepository.save(cupcake);
        return toDTO(salvo);
    }

    public List<CupcakeDTO> listarTodos() {
        return cupcakeRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Cupcake buscarPorId(Long id) {
        return cupcakeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupcake não encontrado"));
    }

    public void deletar(Long id) {
        if (!cupcakeRepository.existsById(id)) {
            throw new RuntimeException("Cupcake não encontrado");
        }
        cupcakeRepository.deleteById(id);
    }

    public CupcakeDTO atualizar(Long id, CupcakeDTO dto) {
        Cupcake cupcakeExistente = cupcakeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupcake não encontrado"));

        // Atualiza os campos do cupcake existente
        cupcakeExistente.setNome(dto.getNome());
        cupcakeExistente.setDescricao(dto.getDescricao());
        cupcakeExistente.setPreco(dto.getPreco());
        cupcakeExistente.setEstoque(dto.getEstoque());
        cupcakeExistente.setImagemUrl(dto.getImagemUrl());
        Cupcake atualizado = cupcakeRepository.save(cupcakeExistente);
        return toDTO(atualizado);
    }
}
