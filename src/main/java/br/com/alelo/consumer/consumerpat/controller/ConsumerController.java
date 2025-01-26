package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    ConsumerService service;

    @Autowired
    ExtractRepository extractRepository;


    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {
        log.info("obtendo todos clientes");
        List<ConsumerDTO> consumers = service.findAll();

        return ResponseEntity.ok(consumers);
    }

    /* Cadastrar novos clientes */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createConsumer(@RequestBody ConsumerDTO consumer) {
        service.save(consumer);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateConsumer(@RequestBody ConsumerDTO consumer) {
        service.save(consumer);
    }

}
