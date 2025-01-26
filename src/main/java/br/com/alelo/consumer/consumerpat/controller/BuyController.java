package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.response.consumer.ExtractResponseDTO;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/extracts")
public class BuyController {

    @Autowired
    private ExtractService extractService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<Void> buy(@RequestBody PurchaseRequestDTO purchase){

        extractService.buy(purchase);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cards/{cardNumber}")
    public ResponseEntity<List<ExtractResponseDTO>> getExtract(@PathVariable(name = "cardNumber") int cardNumber){
        List<ExtractResponseDTO> extractResponseDTOS = extractService.getExtractByCardNumber(cardNumber);
        return ResponseEntity.ok(extractResponseDTOS);
    }
}
