package br.com.alelo.consumer.consumerpat.util.mapping;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;

import java.util.List;

public class CardDTOMapping {
    public static List<Card> toEntities(ConsumerDTO consumerDTO){
        List<Card> cards = consumerDTO.getCards().stream().map(cardDTO -> {
            return Card.builder()
                    .cardNumber(cardDTO.getCardNumber())
                    .cardBalance(cardDTO.getCardBalance())
                    .cardType(CardType.fromString(cardDTO.getCardType()))
                    .build();
        }).collect(java.util.stream.Collectors.toList());

        return cards;
    }
}
