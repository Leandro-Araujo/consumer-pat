package br.com.alelo.consumer.consumerpat.dto.response.consumer;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ConsumerDTO {

    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;

    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    private List<CardDTO> cards;
}
