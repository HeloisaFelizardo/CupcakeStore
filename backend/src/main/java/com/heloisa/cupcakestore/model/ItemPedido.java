package com.heloisa.cupcakestore.model;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    private Long id;
    private Cupcake cupcake;
    private int quantidade;
    private BigDecimal precoUnitario;
    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
