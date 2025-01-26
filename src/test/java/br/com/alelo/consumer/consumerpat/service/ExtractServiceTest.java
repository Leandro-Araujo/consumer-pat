package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.response.consumer.ExtractResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.extract.ExtractDrugStoreStrategy;
import br.com.alelo.consumer.consumerpat.service.extract.ExtractDrugStoreStrategyTest;
import br.com.alelo.consumer.consumerpat.service.extract.ExtractFoodStrategy;
import br.com.alelo.consumer.consumerpat.service.extract.ExtractFuelServiceStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExtractServiceTest {

    ExtractService extractService;

    @Mock
    private ExtractFoodStrategy extractFoodStrategy;

    @Mock
    private ExtractDrugStoreStrategy extractDrugStoreStrategy;

    @Mock
    private ExtractFuelServiceStrategy extractFuelServiceStrategy;
    @Mock
    private ExtractRepository extractRepository;


    @BeforeEach
    public void setUp() {
        extractService = new ExtractService(
                extractFoodStrategy,
                extractDrugStoreStrategy,
                extractFuelServiceStrategy,
                extractRepository);
    }

    @Test
    public void shouldGetExtractByCardNumber() {

        when(extractRepository.findByCardCardNumber(anyInt())).thenReturn(List.of(Extract.builder()
                .id(1)
                        .establishment(Establishment.builder()
                                .id(1L)
                                .establishmentName("name")
                                .establishmentType(1)
                                .build())
                .dateBuy(new Date())
                .card(Card.builder()
                        .id(1)
                        .cardNumber(1)
                        .cardType(CardType.FOOD)
                        .build())
                .value(1.0)
                .build()));

        List<ExtractResponseDTO> extractResponseDTOS = extractService.getExtractByCardNumber(1);

        assertEquals(1, extractResponseDTOS.size());
    }

    @Test
    public void shouldBuyInFoodEstablishment(){
        PurchaseRequestDTO purchaseRequestDTO = PurchaseRequestDTO.builder()
                .establishmentType(1)
                .cardNumber(1)
                .value(1.0)
                .build();
        extractService.buy(purchaseRequestDTO);

        verify(extractFoodStrategy).buy(purchaseRequestDTO);
    }

    @Test
    public void shouldBuyInDrugStoreEstablishment(){
        PurchaseRequestDTO purchaseRequestDTO = PurchaseRequestDTO.builder()
                .establishmentType(2)
                .cardNumber(1)
                .value(1.0)
                .build();
        extractService.buy(purchaseRequestDTO);

        verify(extractDrugStoreStrategy).buy(purchaseRequestDTO);
    }

    @Test
    public void shouldBuyInFuelEstablishment(){
        PurchaseRequestDTO purchaseRequestDTO = PurchaseRequestDTO.builder()
                .establishmentType(3)
                .cardNumber(1)
                .value(1.0)
                .build();
        extractService.buy(purchaseRequestDTO);

        verify(extractFuelServiceStrategy).buy(purchaseRequestDTO);
    }

}
