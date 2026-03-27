package com.heloisa.cupcakestore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
public class Cupcake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private String imagemUrl;

    public boolean possuiEstoque() {
        return estoque != null && estoque > 0;
    }

}
