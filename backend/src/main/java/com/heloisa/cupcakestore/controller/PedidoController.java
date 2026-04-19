package com.heloisa.cupcakestore.controller;

import com.heloisa.cupcakestore.dto.CupcakeDTO;
import com.heloisa.cupcakestore.model.Pedido;
import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.service.CupcakeService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                                HttpSession session) {
        Pedido pedido = getPedido(session);
        Cupcake cupcake = cupcakeService.buscarPorId(id);

        pedido.adicionarItem(cupcake, quantidade);
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
}