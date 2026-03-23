package com.heloisa.cupcakestore.model;

import jakarta.persistence.*;

@Entity
public class Cupcake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrição;
    private double preço;
    private Integer estoque;
    private String imagemUrl;

    public boolean possuiEstoque() {
        return estoque != null && estoque > 0;
    }

}
