package com.carlosesc.recsystem.controller.cliente;

import com.carlosesc.recsystem.controller.servico.facade.ServicoServiceFacade;
import com.carlosesc.recsystem.controller.servico.facade.to.ServicoTO;
import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<List<Cliente>> criar(@RequestBody Cliente cliente) {
        clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> carregar(@PathVariable("clienteId") Long clienteId) {
        Optional<Cliente> optionalCliente = clienteService.carregar(clienteId);
        return optionalCliente
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
