package com.carlosesc.recsystem.service;

import com.carlosesc.recsystem.entity.assinatura.Assinatura;
import com.carlosesc.recsystem.entity.assinatura.AssinaturaRepository;
import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.entity.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaService {

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    public void assinar(Integer aniversario, Cliente cliente, Servico servico) {
        assinaturaRepository.save(new Assinatura(aniversario, servico, cliente));
    }
}
