package com.carlosesc.recsystem.controller.cliente.facade.to.assembler;

import com.carlosesc.recsystem.controller.cliente.facade.to.AssinaturaTO;
import com.carlosesc.recsystem.controller.cliente.facade.to.ClienteTO;
import com.carlosesc.recsystem.controller.cliente.facade.to.ContatoTO;
import com.carlosesc.recsystem.controller.cliente.facade.to.EnderecoTO;
import com.carlosesc.recsystem.controller.servico.facade.to.ServicoTO;
import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.entity.cliente.Endereco;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClienteAssembler {
    private ClienteAssembler(){}

    public static ClienteTO mapClienteTO(Cliente cliente) {
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
                    assinaturaTO.setServico(servicoTO);
                    return assinaturaTO;
                }).collect(toList()));

        return clienteTO;
    }
}
