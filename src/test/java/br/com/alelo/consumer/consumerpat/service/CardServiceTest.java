package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldBeSuccessWhenSetBalance() {
        when(cardRepository.findCardByCardNumber(anyInt())).thenReturn(Optional.of(new Card(1, 1, 1.0, CardType.FOOD, null)));

        cardService.setBalance(1, 1.0);

        verify(cardRepository).save(any(Card.class));
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenSetBalance() {
        when(cardRepository.findCardByCardNumber(anyInt())).thenReturn(Optional.empty());

        try {
            cardService.setBalance(1, 1.0);
        } catch (Exception e) {
            assert e.getMessage().equals("Cartão não encontrado");
        }
    }


}
