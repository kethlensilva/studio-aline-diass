package com.studio.aline.dias.controller;

import com.studio.aline.dias.model.Pedido;
import com.studio.aline.dias.model.ProdutoCarrinho;
import com.studio.aline.dias.service.CarrinhoService;
import com.studio.aline.dias.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/finalizar")
public ResponseEntity<Pedido> finalizarPedido(@RequestBody Pedido pedido) {
    List<ProdutoCarrinho> itensCarrinho = carrinhoService.getProdutosNoCarrinho();
    
    // Supondo que Pedido tem List<ProdutoCarrinho> ou algo similar
    pedido.setProdutos(new ArrayList<>(itensCarrinho)); 

    pedido.setDataPedido(LocalDateTime.now());
    Pedido salvo = pedidoService.salvarPedido(pedido);
    carrinhoService.limparCarrinho();
    return ResponseEntity.ok(salvo);
}

    //  API - Listar pedidos
    @GetMapping
    @ResponseBody
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    //  API - Buscar pedido por ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  Página - Detalhes
    @GetMapping("/detalhesc")
    public String detalhesc() {
        return "detalhesc";  // Thymeleaf vai procurar em: resources/templates/detalhesc.html
    }
}
