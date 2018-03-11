package com.carlosesc.recsystem.controller.cliente.facade.to;

import lombok.Data;

@Data
public class EnderecoTO {

    private Integer numero;

    private String descricao;

    private String cep;
}
