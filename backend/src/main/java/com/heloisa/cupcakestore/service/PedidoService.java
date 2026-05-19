package com.heloisa.cupcakestore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.heloisa.cupcakestore.repository.CupcakeRepository;
import com.heloisa.cupcakestore.repository.PedidoRepository;
import com.heloisa.cupcakestore.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import com.heloisa.cupcakestore.model.Cupcake;
import com.heloisa.cupcakestore.model.ItemPedido;
import com.heloisa.cupcakestore.model.Pedido;
import com.heloisa.cupcakestore.model.Usuario;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CupcakeRepository cupcakeRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository,
            CupcakeRepository cupcakeRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.cupcakeRepository = cupcakeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private Usuario buscarUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public void finalizarPedido(Pedido pedido) {

        Usuario usuario = buscarUsuarioLogado();

        for (ItemPedido item : pedido.getItens()) {
            // busca com lock
            Cupcake cupcake = cupcakeRepository
                    .buscarPorIdComLock(item.getCupcake().getId())
                    .orElseThrow();

            // valida estoque
            if (cupcake.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException(
                        "Estoque insuficiente para " + cupcake.getNome());
            }

            // baixa estoque
            cupcake.setEstoque(
                    cupcake.getEstoque() - item.getQuantidade());
        }

        pedido.setCliente(usuario);
        pedido.setDataPedido(LocalDateTime.now());
        pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPedidosDoUsuarioLogado() {
        Usuario usuario = buscarUsuarioLogado();
        return pedidoRepository.findByClienteIdOrderByDataPedidoDesc(usuario.getId());
    }
}
