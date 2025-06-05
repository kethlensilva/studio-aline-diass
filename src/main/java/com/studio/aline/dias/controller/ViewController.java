 package com.studio.aline.dias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping({"/", "/home"})
    public String exibirPaginaInicial() {
        return "home"; // renderiza home.html
    }

     @GetMapping({"/produtos"})
    public String exibirPaginaProdutos() {
        return "produtos"; // renderiza produtos.html
    }


    @GetMapping("/login")
    public String exibirTelaLogin() {
        return "login"; // renderiza login.html
    }

    @GetMapping("/cadastro")
    public String exibirTelaCadastro() {
        return "cadastro";  // renderiza cadastro.html
    }
    @GetMapping("/checkout1")
    public String exibirTelaCheckout1() {
        return "checkout1"; // renderiza checkout1.html
    }
    @GetMapping("/checkout2")
    public String exibirTelaCheckout2() {
        return "checkout2"; // renderiza checkout2.html
    }
    @GetMapping("/carrinho")
    public String exibirTelaCarrinho() {
        return "carrinho"; // renderiza carrinho.html
    }

    @GetMapping("/detalhesc")
    public String exibirTelaDetahles() {
        return "detalhesc"; // renderiza detalhesc.html
    }

    @GetMapping("/h2-console")
    public String exibirTelaH2Console() {
        return "h2-console"; // renderiza h2-console.html
    }
}
