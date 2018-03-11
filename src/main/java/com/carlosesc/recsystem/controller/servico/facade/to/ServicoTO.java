package com.carlosesc.recsystem.controller.servico.facade.to;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicoTO {
    private long id;
    private BigDecimal valor;
    private String descricao;
}
