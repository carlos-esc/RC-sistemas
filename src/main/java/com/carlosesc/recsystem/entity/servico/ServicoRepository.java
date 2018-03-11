package com.carlosesc.recsystem.entity.servico;

import com.carlosesc.recsystem.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository <Servico, Long> {

}
