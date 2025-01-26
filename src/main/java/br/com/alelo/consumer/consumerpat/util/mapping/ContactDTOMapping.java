package br.com.alelo.consumer.consumerpat.util.mapping;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Contact;

public class ContactDTOMapping {
    public static Contact toEntity(ConsumerDTO consumerDTO){
        return Contact.builder()
                .mobilePhoneNumber(consumerDTO.getMobilePhoneNumber())
                .residencePhoneNumber(consumerDTO.getResidencePhoneNumber())
                .phoneNumber(consumerDTO.getPhoneNumber())
                .email(consumerDTO.getEmail())
                .build();
    }
}
