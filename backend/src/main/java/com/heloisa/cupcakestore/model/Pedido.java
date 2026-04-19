package com.heloisa.cupcakestore.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Long id;
    private LocalDateTime dataPedido;
    private Usuario cliente;
    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionarItem(Cupcake cupcake, int quantidade) {

        if (quantidade <= 0) {
            removerItem(cupcake.getId());
            return;
        }

        if (itens == null) {
            itens = new ArrayList<>();
        }

        for (ItemPedido item : itens) {
            if (item.getCupcake().getId().equals(cupcake.getId())) {
                item.setQuantidade(item.getQuantidade() + quantidade);

                if (item.getQuantidade() <= 0) {
                    removerItem(cupcake.getId());
                }

                return;
            }
        }

        ItemPedido novoItem = new ItemPedido();
        novoItem.setCupcake(cupcake);
        novoItem.setQuantidade(quantidade);
        novoItem.setPrecoUnitario(cupcake.getPreco());

        itens.add(novoItem);
    }

    public BigDecimal getValorTotal() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void removerItem(Long cupcakeId) {
        itens.removeIf(item ->
                item.getCupcake() != null &&
                        item.getCupcake().getId().equals(cupcakeId)
        );
    }
}
