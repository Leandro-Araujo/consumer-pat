package br.com.alelo.consumer.consumerpat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseRequestDTO {

    private Integer establishmentType;

    private String establishmentName;

    private Integer cardNumber;

    private String productDescription;

    private Double value;
}
