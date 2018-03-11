package com.carlosesc.recsystem.service;

import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.entity.cliente.ClienteRepository;
import com.carlosesc.recsystem.entity.servico.Servico;
import com.carlosesc.recsystem.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void salvar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> carregar(long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public void assinar(Cliente cliente, Servico servico) {
        cliente.assinar(servico);
        clienteRepository.save(cliente);
    }
}
