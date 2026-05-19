package com.heloisa.cupcakestore.repository;

import com.heloisa.cupcakestore.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);

    List<Pedido> findByClienteIdOrderByDataPedidoDesc(Long clienteId);
}
