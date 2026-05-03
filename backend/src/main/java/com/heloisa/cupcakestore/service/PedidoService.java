package com.heloisa.cupcakestore.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.heloisa.cupcakestore.repository.CupcakeRepository;
import com.heloisa.cupcakestore.repository.PedidoRepository;

import jakarta.transaction.Transactional;

import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.model.ItemPedido;
import com.heloisa.cupcakestore.model.Pedido;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CupcakeRepository cupcakeRepository;

    public PedidoService(PedidoRepository pedidoRepository,
            CupcakeRepository cupcakeRepository) {
        this.pedidoRepository = pedidoRepository;
        this.cupcakeRepository = cupcakeRepository;
    }

    @Transactional
    public void finalizarPedido(Pedido pedido) {

        for (ItemPedido item : pedido.getItens()) {

            // busca com lock
            Cupcake cupcake = cupcakeRepository
                    .buscarPorIdComLock(item.getCupcake().getId())
                    .orElseThrow();

            // valida estoque
            if (cupcake.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException(
                        "Estoque insuficiente para " + cupcake.getNome());
            }

            // baixa estoque
            cupcake.setEstoque(
                    cupcake.getEstoque() - item.getQuantidade());
        }

        pedido.setDataPedido(LocalDateTime.now());
        pedidoRepository.save(pedido);
    }
}
