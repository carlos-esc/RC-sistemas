package com.carlosesc.recsystem.controller.servico.facade;

import com.carlosesc.recsystem.controller.servico.facade.to.ServicoTO;
import com.carlosesc.recsystem.entity.servico.Servico;
import com.carlosesc.recsystem.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class ServicoServiceFacade {

    @Autowired
    private ServicoService servicoService;

    public void salvar(ServicoTO servicoTO) {
        servicoService.salvar(new Servico(servicoTO.getValor(), servicoTO.getDescricao()));
    }

    public void alterar(ServicoTO servicoTO) {
        servicoService.alterar(new Servico(servicoTO.getId(), servicoTO.getValor(), servicoTO.getDescricao()));
    }

    public Optional<ServicoTO> carregar(long id) {
       return servicoService.carregar(id)
                .map(servico -> Optional.of(getServicoTO(servico)))
                .orElse(Optional.empty());
    }

    private ServicoTO getServicoTO(Servico servico) {
        ServicoTO servicoTO = new ServicoTO();
        servicoTO.setDescricao(servico.getDescricao());
        servicoTO.setValor(servico.getValor());
        return servicoTO;
    }

    public List<ServicoTO> buscarTodos() {
        return servicoService.buscarTodos()
                .stream()
                .map(this::getServicoTO)
                .collect(toList());
    }
}
