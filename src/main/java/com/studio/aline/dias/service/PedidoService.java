package com.studio.aline.dias.service;

import com.studio.aline.dias.model.Pedido;
import com.studio.aline.dias.service.CarrinhoService;
import com.studio.aline.dias.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    public Pedido salvarPedido(Pedido pedido) {
        pedido.setDataPedido(LocalDateTime.now());

        // Obtem os produtos do carrinho da sessão
        pedido.setProdutos(carrinhoService.getProdutosNoCarrinho());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Limpa o carrinho após salvar o pedido
        carrinhoService.limparCarrinho();

        return pedidoSalvo;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }
}
