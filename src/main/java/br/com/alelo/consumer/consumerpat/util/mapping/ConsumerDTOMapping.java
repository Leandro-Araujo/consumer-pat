package br.com.alelo.consumer.consumerpat.util.mapping;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.response.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;
import java.util.stream.Collectors;

public class ConsumerDTOMapping {

    public static Consumer toEntity(ConsumerDTO consumerDTO){
        return Consumer.builder()
                .id(consumerDTO.getId())
                .name(consumerDTO.getName())
                .documentNumber(consumerDTO.getDocumentNumber())
                .birthDate(consumerDTO.getBirthDate())
                .contact(ContactDTOMapping.toEntity(consumerDTO))
                .address(AddressDTOMapping.toEntity(consumerDTO))
                .cards(CardDTOMapping.toEntities(consumerDTO))
                .build();
    }

    public static List<ConsumerDTO> toDTO(List<Consumer> consumers){
        return consumers.stream().map(consumer -> {

                    ConsumerDTO consumerResponse = ConsumerDTO.builder()
                            .id(consumer.getId())
                            .name(consumer.getName())
                            .documentNumber(consumer.getDocumentNumber())
                            .birthDate(consumer.getBirthDate())
                            .build();
                    if (consumer.getContact() != null) {
                        consumerResponse.setMobilePhoneNumber(consumer.getContact().getMobilePhoneNumber());
                        consumerResponse.setResidencePhoneNumber(consumer.getContact().getResidencePhoneNumber());
                        consumerResponse.setPhoneNumber(consumer.getContact().getPhoneNumber());
                        consumerResponse.setEmail(consumer.getContact().getEmail());
                    }
                    if (consumer.getAddress() != null) {
                        consumerResponse.setStreet(consumer.getAddress().getStreet());
                        consumerResponse.setNumber(consumer.getAddress().getNumber());
                        consumerResponse.setCity(consumer.getAddress().getCity());
                        consumerResponse.setCountry(consumer.getAddress().getCountry());
                        consumerResponse.setPortalCode(consumer.getAddress().getPortalCode());
                    }
                    if (consumer.getCards() != null) {
                        List<CardDTO> cards = consumer.getCards().stream().map(card -> {
                            return CardDTO.builder()
                                    .cardNumber(card.getCardNumber())
                                    .cardBalance(card.getCardBalance())
                                    .cardType(card.getCardType().getName())
                                    .build();
                        }).collect(Collectors.toList());
                        consumerResponse.setCards(cards);
                    }

                    return consumerResponse;
                }
        ).collect(Collectors.toList());
    }
}
