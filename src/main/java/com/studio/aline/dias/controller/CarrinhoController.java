package com.studio.aline.dias.controller;

import com.studio.aline.dias.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public ResponseEntity<?> listarProdutosNoCarrinho() {
        return ResponseEntity.ok(carrinhoService.getProdutosNoCarrinho());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarProduto(@RequestBody Map<String, Object> payload) {
        Long produtoId = Long.valueOf(payload.get("produtoId").toString());
        int quantidade = Integer.parseInt(payload.getOrDefault("quantidade", 1).toString());

        carrinhoService.adicionarProduto(produtoId, quantidade);
        return ResponseEntity.ok("Produto adicionado no carrinho");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarQuantidade(@RequestBody Map<String, Object> payload) {
        Long produtoId = Long.valueOf(payload.get("produtoId").toString());
        int quantidade = Integer.parseInt(payload.get("quantidade").toString());

        carrinhoService.atualizarQuantidade(produtoId, quantidade);
        return ResponseEntity.ok("Quantidade atualizada");
    }

    @DeleteMapping("/limpar")
    public ResponseEntity<String> limparCarrinho() {
        carrinhoService.limparCarrinho();
        return ResponseEntity.ok("Carrinho limpo");
    }
}
