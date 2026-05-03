package com.heloisa.cupcakestore.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    @Transient
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionarItem(Cupcake cupcake, int quantidade) {
        if (quantidade <= 0) {
            removerItem(cupcake.getId());
            return;
        }

        for (ItemPedido item : itens) {
            if (Objects.equals(item.getCupcake().getId(), cupcake.getId())) {
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
        novoItem.setPedido(this);
        itens.add(novoItem);
    }

    public BigDecimal getValorTotal() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void removerItem(Long cupcakeId) {
        itens.removeIf(item -> item.getCupcake() != null &&
                Objects.equals(item.getCupcake().getId(), cupcakeId));
    }

    @PrePersist
    public void prePersist() {
        this.dataPedido = LocalDateTime.now();
    }
}
