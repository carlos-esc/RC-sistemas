package com.carlosesc.recsystem.controller.cliente.facade;

import com.carlosesc.recsystem.controller.cliente.facade.to.ClienteTO;
import com.carlosesc.recsystem.controller.cliente.facade.to.CriarClienteTO;
import com.carlosesc.recsystem.controller.cliente.facade.to.EnderecoTO;
import com.carlosesc.recsystem.controller.cliente.facade.to.assembler.ClienteAssembler;
import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.entity.cliente.Contato;
import com.carlosesc.recsystem.entity.cliente.Endereco;
import com.carlosesc.recsystem.entity.servico.Servico;
import com.carlosesc.recsystem.service.ClienteService;
import com.carlosesc.recsystem.service.ServicoService;
import com.carlosesc.recsystem.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class ClienteServiceFacade {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;


    public void salvar(CriarClienteTO clienteTO) {
        List<Contato> contatos = clienteTO.getContatos()
                .stream()
                .map(contatoTO -> new Contato(contatoTO.getNumero()))
                .collect(toList());

        EnderecoTO enderecoTO = clienteTO.getEndereco();
        Endereco endereco = new Endereco(enderecoTO.getNumero(), enderecoTO.getDescricao(), enderecoTO.getCep());

        Cliente cliente = new Cliente(clienteTO.getNome(),
                clienteTO.getCpf(),
                clienteTO.getEmail(),
                endereco,
                contatos);

        clienteService.salvar(cliente);
    }

    public Optional<ClienteTO> carregar(long clienteId) {
        return clienteService.carregar(clienteId)
                .map(cliente -> Optional.of(ClienteAssembler.mapClienteTO(cliente)))
                .orElse(Optional.empty());
    }

     public void assinarServico(long clienteId, long servicoId) {
        Cliente cliente = clienteService.carregar(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente " + clienteId + " nao encontrado"));

        Servico servico = servicoService.carregar(servicoId)
                .orElseThrow(() -> new NotFoundException("Servico " + servicoId + " nao encontrado"));

        clienteService.assinar(cliente, servico);
    }

    public List<ClienteTO> buscarTodos() {
        return clienteService.buscarTodos()
                .stream()
                .map(ClienteAssembler::mapClienteTO)
                .collect(toList());
    }
}
