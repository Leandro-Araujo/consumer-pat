package br.com.alelo.consumer.consumerpat.util.mapping;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;

public class AddressDTOMapping {
    public static Address toEntity(ConsumerDTO consumerDTO){
        return Address.builder()
                .street(consumerDTO.getStreet())
                .number(consumerDTO.getNumber())
                .city(consumerDTO.getCity())
                .country(consumerDTO.getCountry())
                .portalCode(consumerDTO.getPortalCode())
                .build();
    }
}
