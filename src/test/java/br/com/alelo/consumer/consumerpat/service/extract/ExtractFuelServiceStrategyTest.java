package br.com.alelo.consumer.consumerpat.service.extract;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequestDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExtractFuelServiceStrategyTest {
    @InjectMocks
    private ExtractFuelServiceStrategy extractFuelServiceStrategy;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private EstablishmentRepository establishmentRepository;
    @Mock
    private ExtractRepository extractRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFailBecauseCardNotFound() {
        when(cardRepository.findCardByCardNumber(1)).thenReturn(Optional.empty());
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        try {
            extractFuelServiceStrategy.buy(purchaseRequestDTO);
        } catch (Exception e) {
            assert e.getMessage().equals("Cartão não encontrado.");
        }
    }

    @Test
    public void shouldFailBecauseCardTypeIsInvalid(){
        when(cardRepository.findCardByCardNumber(1)).thenReturn(Optional.of(Card.builder()
                        .id(1)
                        .cardType(CardType.FOOD)
                        .cardNumber(1)
                .build()));
        PurchaseRequestDTO purchaseRequestDTO = PurchaseRequestDTO.builder()
                .cardNumber(1)
                .value(100.0)
                .build();
        try {
            extractFuelServiceStrategy.buy(purchaseRequestDTO);
        } catch (Exception e) {
            assert e.getMessage().equals("Tipo de cartão inválido");
        }
    }

    @Test
    public void shouldFailBecauseCardBalanceIsInsufficient(){
        when(cardRepository.findCardByCardNumber(1)).thenReturn(Optional.of(Card.builder()
                        .id(1)
                        .cardType(CardType.FUEL)
                        .cardNumber(1)
                        .cardBalance(0.0)
                .build()));
        PurchaseRequestDTO purchaseRequestDTO = PurchaseRequestDTO.builder()
                .cardNumber(1)
                .value(100.0)
                .build();
        try {
            extractFuelServiceStrategy.buy(purchaseRequestDTO);
        } catch (Exception e) {
            assert e.getMessage().equals("Saldo insuficiente");
        }
    }

    @Test
    public void testShouldSaveExtractWithTaxCorrectly(){
        when(cardRepository.findCardByCardNumber(1)).thenReturn(Optional.of(Card.builder()
                        .id(1)
                        .cardType(CardType.FUEL)
                        .cardNumber(1)
                        .cardBalance(100.0)
                .build()));
        PurchaseRequestDTO purchaseRequestDTO = PurchaseRequestDTO.builder()
                .cardNumber(1)
                .value(100.0)
                .build();
        extractFuelServiceStrategy.buy(purchaseRequestDTO);
        verify(extractRepository).save(any());
        assert purchaseRequestDTO.getValue().equals(135.0);
    }


}
