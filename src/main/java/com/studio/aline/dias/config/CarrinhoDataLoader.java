package com.studio.aline.dias.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.studio.aline.dias.model.Produto;
import com.studio.aline.dias.repository.ProdutoRepository;

@Component
public class CarrinhoDataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public CarrinhoDataLoader(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cadastra produtos básicos para teste
        Produto produto1 = new Produto();
        produto1.setNome("Linha Lumera Hair");
        produto1.setPreco(150.0);

        Produto produto2 = new Produto();
        produto2.setNome("Oléo Lumera Hair");
        produto2.setPreco(50.0);

        Produto produto3 = new Produto();
        produto3.setNome("Lumina Blond Hair");
        produto3.setPreco(119.99);

        Produto produto4 = new Produto();
        produto4.setNome("Cherrie Hair");
        produto4.setPreco(50.14);
        
        Produto produto5 = new Produto();
        produto5.setNome("Luminous Mask");
        produto5.setPreco(29.99);

        Produto produto6 = new Produto();
        produto6.setNome("Kit Tônico Capilar");
        produto6.setPreco(130.00);

        Produto produto7 = new Produto();
        produto7.setNome("Tônico Fortalecimento");
        produto7.setPreco(69.90);

        Produto produto8 = new Produto();
        produto8.setNome("Tônico Mega Hair");
        produto8.setPreco(69.90);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);
        produtoRepository.save(produto4);
        produtoRepository.save(produto5);
        produtoRepository.save(produto6);
        produtoRepository.save(produto7);
        produtoRepository.save(produto8);



        System.out.println("Produtos iniciais cadastrados.");
    }
}
