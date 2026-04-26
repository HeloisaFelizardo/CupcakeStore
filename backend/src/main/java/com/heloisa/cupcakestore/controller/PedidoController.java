package com.heloisa.cupcakestore.controller;

import com.heloisa.cupcakestore.model.Pedido;
import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.service.CupcakeService;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    private final CupcakeService cupcakeService;

    public PedidoController(CupcakeService cupcakeService) {
        this.cupcakeService = cupcakeService;
    }

    private Pedido getPedido(HttpSession session) {
        Pedido pedido = (Pedido) session.getAttribute("pedido");
        if (pedido == null) {
            pedido = new Pedido();
            session.setAttribute("pedido", pedido);
        }
        return pedido;
    }

    @PostMapping("/adicionar")
    public String adicionarItem(@RequestParam Long id,
            @RequestParam int quantidade,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        Pedido pedido = getPedido(session);
        Cupcake cupcake = cupcakeService.buscarPorId(id);

        pedido.adicionarItem(cupcake, quantidade);
        redirectAttributes.addFlashAttribute("sucesso", true);
        return "redirect:/";
    }

    @PostMapping("/remover")
    public String removerItem(@RequestParam Long id, HttpSession session) {
        Pedido pedido = getPedido(session);
        pedido.removerItem(id);
        return "redirect:/pedido";
    }

    @GetMapping
    public String verCarrinho(Model model, HttpSession session) {
        Pedido pedido = getPedido(session);
        model.addAttribute("pedido", pedido);
        return "carrinho";
    }

    @PostMapping("/finalizar")
    public String finalizarPedido(HttpSession session) {

        Pedido pedido = getPedido(session);

        if (pedido.getItens().isEmpty()) {
            return "redirect:/pedido";
        }

        // simula finalização
        pedido.setDataPedido(LocalDateTime.now());

        // limpa carrinho
        session.removeAttribute("pedido");

        return "redirect:/pedido/sucesso";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
}