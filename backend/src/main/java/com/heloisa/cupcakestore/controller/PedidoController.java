package com.heloisa.cupcakestore.controller;

import com.heloisa.cupcakestore.model.Pedido;
import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.model.ItemPedido;
import com.heloisa.cupcakestore.service.CupcakeService;
import com.heloisa.cupcakestore.service.PedidoService;

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
    private final PedidoService pedidoService;

    public PedidoController(CupcakeService cupcakeService, PedidoService pedidoService) {
        this.cupcakeService = cupcakeService;
        this.pedidoService = pedidoService;
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
    public String finalizarPedido(HttpSession session,
            RedirectAttributes redirectAttributes) {

        Pedido pedido = getPedido(session);

        if (pedido.getItens().isEmpty()) {
            return "redirect:/pedido";
        }

        try {
            pedidoService.finalizarPedido(pedido);
            session.removeAttribute("pedido");

            return "redirect:/pedido/sucesso";

        } catch (RuntimeException e) {

            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/pedido";
        }
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
}