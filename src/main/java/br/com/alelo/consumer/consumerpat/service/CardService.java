package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public void setBalance(Integer cardNumber, Double value)  {
        Card card = cardRepository.findCardByCardNumber(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado"));
        card.setCardBalance(card.getCardBalance() + value);

        cardRepository.save(card);
    }
}
