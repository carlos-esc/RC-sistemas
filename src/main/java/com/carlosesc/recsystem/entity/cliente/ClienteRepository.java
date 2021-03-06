package com.carlosesc.recsystem.entity.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    List<Cliente> findByNome(String nome);
}
