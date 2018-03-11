package com.carlosesc.recsystem.service;

import com.carlosesc.recsystem.entity.servico.Servico;
import com.carlosesc.recsystem.entity.servico.ServicoRepository;
import com.carlosesc.recsystem.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public void salvar(Servico servico) {
        servicoRepository.save(servico);
    }

    public void alterar(Servico servico) {
        servicoRepository.save(carregar(servico.getId())
                .map(servicoDoBanco -> {
                    servicoDoBanco.setDescricao(servico.getDescricao());
                    servicoDoBanco.setValor(servico.getValor());
                    return servicoDoBanco;
                })
                .orElseThrow(() -> new NotFoundException("Servico " + servico.getId() + " nao encontrado")));
    }

    public Optional<Servico> carregar(long id) {
        return servicoRepository.findById(id);
    }

    public List<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }
}
