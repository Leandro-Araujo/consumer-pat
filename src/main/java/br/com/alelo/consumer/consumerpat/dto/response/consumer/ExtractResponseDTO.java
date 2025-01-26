package br.com.alelo.consumer.consumerpat.dto.response.consumer;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExtractResponseDTO {

        private String productDescription;
        private String dateBuy;
        private Double value;
        private String establishmentName;

}
