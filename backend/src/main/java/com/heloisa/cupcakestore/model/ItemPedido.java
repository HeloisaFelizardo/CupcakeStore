package com.heloisa.cupcakestore.model;

import lombok.*;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cupcake_id")
    private Cupcake cupcake;

    private int quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    public BigDecimal getSubtotal() {
        if (precoUnitario == null)
            return BigDecimal.ZERO;

        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
