package com.studio.aline.dias.service;

import com.studio.aline.dias.model.Produto;
import com.studio.aline.dias.model.ProdutoCarrinho;
import com.studio.aline.dias.repository.ProdutoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private static final String SESSION_CARRINHO = "CARRINHO";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private HttpSession session;

    public List<ProdutoCarrinho> getProdutosNoCarrinho() {
        List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) session.getAttribute(SESSION_CARRINHO);
        if (carrinho == null) {
            carrinho = new ArrayList<>();
            session.setAttribute(SESSION_CARRINHO, carrinho);
        }
        return carrinho;
    }

    public void adicionarProduto(Long produtoId, int quantidade) {
        List<ProdutoCarrinho> carrinho = getProdutosNoCarrinho();
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
        if (produtoOpt.isEmpty()) return;

        Produto produto = produtoOpt.get();

        Optional<ProdutoCarrinho> existente = carrinho.stream()
                .filter(pc -> pc.getProduto().getId().equals(produtoId))
                .findFirst();

        if (existente.isPresent()) {
            ProdutoCarrinho pc = existente.get();
            pc.setQuantidade(pc.getQuantidade() + quantidade);
        } else {
            ProdutoCarrinho pc = new ProdutoCarrinho();
            pc.setProduto(produto);
            pc.setQuantidade(quantidade);
            carrinho.add(pc);
        }
        session.setAttribute(SESSION_CARRINHO, carrinho);
    }

    public void limparCarrinho() {
        session.removeAttribute(SESSION_CARRINHO);
    }

    public void atualizarQuantidade(Long produtoId, int quantidade) {
        List<ProdutoCarrinho> carrinho = getProdutosNoCarrinho();

        for (ProdutoCarrinho pc : carrinho) {
            if (pc.getProduto().getId().equals(produtoId)) {
                pc.setQuantidade(quantidade);
                break;
            }
        }
        session.setAttribute(SESSION_CARRINHO, carrinho);
    }
}
