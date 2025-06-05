package com.studio.aline.dias.service;

import com.studio.aline.dias.model.Endereco;
import com.studio.aline.dias.repository.EnderecoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private HttpSession session;

    public Endereco salvarEndereco(Endereco endereco) {
        Endereco salvo = enderecoRepository.save(endereco);
        session.setAttribute("enderecoAtualId", salvo.getId());
        return salvo;
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Optional<Endereco> getEnderecoAtual() {
        Long id = (Long) session.getAttribute("enderecoAtualId");
        if (id == null) return Optional.empty();
        return enderecoRepository.findById(id);
    }
}
