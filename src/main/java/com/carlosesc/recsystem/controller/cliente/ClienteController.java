package com.carlosesc.recsystem.controller.cliente;

import com.carlosesc.recsystem.controller.cliente.facade.ClienteServiceFacade;
import com.carlosesc.recsystem.controller.cliente.facade.to.ClienteTO;
import com.carlosesc.recsystem.controller.cliente.facade.to.CriarClienteTO;
import com.carlosesc.recsystem.entity.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceFacade clienteServiceFacade;

    @GetMapping
    public ResponseEntity<List<ClienteTO>> buscarTodos() {
        return ResponseEntity.ok(clienteServiceFacade.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CriarClienteTO clienteTO) {
        clienteServiceFacade.salvar(clienteTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteTO> carregar(@PathVariable("clienteId") long clienteId) {
        Optional<ClienteTO> optionalClienteTO = clienteServiceFacade.carregar(clienteId);
        return optionalClienteTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{clienteId}/assinaturas")
    public ResponseEntity<Void> assinarServico(@PathVariable("clienteId") long clienteId, @RequestBody long servicoId) {
        clienteServiceFacade.assinarServico(clienteId, servicoId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
