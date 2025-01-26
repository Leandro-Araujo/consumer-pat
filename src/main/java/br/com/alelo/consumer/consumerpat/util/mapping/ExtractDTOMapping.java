package br.com.alelo.consumer.consumerpat.util.mapping;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.ExtractResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;

import java.util.List;
import java.util.stream.Collectors;

public class ExtractDTOMapping {
    public static List<ExtractResponseDTO> mapToDTO(List<Extract> extracts) {
        List<ExtractResponseDTO> extractsDto = extracts.stream().map(extract ->
                ExtractResponseDTO.builder()
                        .productDescription(extract.getProductDescription())
                        .dateBuy(extract.getDateBuy().toString())
                        .value(extract.getValue())
                        .establishmentName(extract.getEstablishment().getEstablishmentName())
                        .build()

        ).collect(Collectors.toList());

        return extractsDto;
    }
}
