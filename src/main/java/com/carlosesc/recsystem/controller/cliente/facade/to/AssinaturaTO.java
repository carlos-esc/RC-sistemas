package com.carlosesc.recsystem.controller.cliente.facade.to;

import com.carlosesc.recsystem.controller.servico.facade.to.ServicoTO;
import lombok.Data;

@Data
public class AssinaturaTO {
    private Integer aniversario;
    private String status;
    private ServicoTO servico;
}
