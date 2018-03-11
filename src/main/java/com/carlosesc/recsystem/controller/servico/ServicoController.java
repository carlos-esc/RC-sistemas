package com.carlosesc.recsystem.controller.servico;

import com.carlosesc.recsystem.controller.servico.facade.ServicoServiceFacade;
import com.carlosesc.recsystem.controller.servico.facade.to.ServicoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {

    @Autowired
    private ServicoServiceFacade servicoServiceFacade;

    @GetMapping
    public ResponseEntity<List<ServicoTO>> buscarTodos() {
        return ResponseEntity.ok(servicoServiceFacade.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<List<ServicoTO>> criar(@RequestBody ServicoTO servicoTO) {
        servicoServiceFacade.salvar(servicoTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<List<ServicoTO>> alterar(@RequestBody ServicoTO servicoTO) {
        servicoServiceFacade.alterar(servicoTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{servicoId}")
    public ResponseEntity<ServicoTO> carregar(@PathVariable("servicoId") Long servicoId) {
        Optional<ServicoTO> servicoOptional = servicoServiceFacade.carregar(servicoId);
        return servicoOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
