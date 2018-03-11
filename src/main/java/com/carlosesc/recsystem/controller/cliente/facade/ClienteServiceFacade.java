package com.carlosesc.recsystem.controller.cliente.facade;

import com.carlosesc.recsystem.controller.cliente.facade.to.*;
import com.carlosesc.recsystem.controller.servico.facade.to.ServicoTO;
import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.entity.cliente.Contato;
import com.carlosesc.recsystem.entity.cliente.Endereco;
import com.carlosesc.recsystem.entity.servico.Servico;
import com.carlosesc.recsystem.service.ClienteService;
import com.carlosesc.recsystem.service.ServicoService;
import com.carlosesc.recsystem.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
                .map(cliente -> Optional.of(mapClienteTO(cliente)))
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
                .map(this::mapClienteTO)
                .collect(toList());
    }

    private ClienteTO mapClienteTO(Cliente cliente) {
        List<ContatoTO> contatoTOs = new ArrayList<>();

        cliente.getContatos()
                .forEach(contato -> {
                    ContatoTO contatoTO = new ContatoTO();
                    contatoTO.setNumero(contato.getNumero());
                    contatoTOs.add(contatoTO);
                });

        EnderecoTO enderecoTO = new EnderecoTO();
        Endereco endereco = cliente.getEndereco();
        enderecoTO.setCep(endereco.getCep());
        enderecoTO.setDescricao(endereco.getDescricao());
        enderecoTO.setNumero(endereco.getNumero());

        ClienteTO clienteTO = new ClienteTO();
        clienteTO.setContatos(contatoTOs);
        clienteTO.setEndereco(enderecoTO);
        clienteTO.setCpf(cliente.getCpf());
        clienteTO.setEmail(cliente.getEmail());
        clienteTO.setNome(cliente.getNome());

        clienteTO.setAssinaturas(cliente.getAssinaturas()
                .stream()
                .map(assinatura -> {
                    ServicoTO servicoTO = new ServicoTO();
                    servicoTO.setDescricao(assinatura.getServico().getDescricao());
                    servicoTO.setValor(assinatura.getServico().getValor());
                    AssinaturaTO assinaturaTO = new AssinaturaTO();
                    assinaturaTO.setAniversario(assinatura.getAniversario());
                    assinaturaTO.setStatus(assinatura.getStatus().name());
                    assinaturaTO.setServicoTO(servicoTO);
                    return assinaturaTO;
                }).collect(toList()));

        return clienteTO;
    }

}
