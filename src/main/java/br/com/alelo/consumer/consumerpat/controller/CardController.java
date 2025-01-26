package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.request.BalanceRequestDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{cardNumber}")
    public ResponseEntity<Void> setBalance(
            @PathVariable(name = "cardNumber") int cardNumber,
            @RequestBody BalanceRequestDTO balanceDTO) {

        cardService.setBalance(cardNumber, balanceDTO.getValue());
        return ResponseEntity.ok().build();
    }
}
