package com.carlosesc.recsystem.controller.cliente.facade.to;

import lombok.Data;

import java.util.List;

@Data
public class CriarClienteTO {
    private String cpf;
    private String email;
    private String nome;
    private List<ContatoTO> contatos;
    private EnderecoTO endereco;
}
