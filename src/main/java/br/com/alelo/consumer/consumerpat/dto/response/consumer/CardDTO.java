package br.com.alelo.consumer.consumerpat.dto.response.consumer;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CardDTO {
    private Integer cardNumber;
    private Double cardBalance;
    private Integer cardTypeCode;
    private String cardType;
}
