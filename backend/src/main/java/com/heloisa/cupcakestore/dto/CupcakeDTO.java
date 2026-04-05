package com.heloisa.cupcakestore.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CupcakeDTO {
    private Long id;

    @NotBlank(message = "O nome do cupcake é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição do cupcake é obrigatória")
    private String descricao;

    @NotNull(message = "O preço do cupcake é obrigatório")
    @Positive(message = "O preço do cupcake deve ser positivo")
    private BigDecimal preco;

    @NotNull(message = "O estoque do cupcake é obrigatório")
    @Min(value = 0, message = "O estoque do cupcake não pode ser negativo")
    private Integer estoque;

    private String imagemUrl;
}
