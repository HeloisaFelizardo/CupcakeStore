package com.heloisa.cupcakestore.controller;

import com.heloisa.cupcakestore.model.Pedido;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("quantidadeCarrinho")
    public int quantidadeCarrinho(HttpSession session) {
        Pedido pedido = (Pedido) session.getAttribute("pedido");

        if (pedido == null || pedido.getItens() == null) {
            return 0;
        }

        return pedido.getItens().stream()
                .mapToInt(item -> item.getQuantidade())
                .sum();
    }
}