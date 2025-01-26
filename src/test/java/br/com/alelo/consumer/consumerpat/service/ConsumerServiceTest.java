package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConsumerServiceTest {
    @InjectMocks
    private ConsumerService consumerService;

    @Mock
    private ConsumerRepository repository;

    @Mock
    private CardRepository cardRepository;

    @Test
    public void shouldFindAllConsumers(){
        List<Consumer> consumers = new ArrayList<>();
        consumers.add(Consumer.builder().id(1).name("Consumer 1").build());
        consumers.add(Consumer.builder().id(2).name("Consumer 2").build());
        consumers.add(Consumer.builder().id(3).name("Consumer 3").build());
        when(repository.findAll()).thenReturn(consumers);

        List<ConsumerDTO> consumersDTO = consumerService.findAll();

        assertEquals(3, consumersDTO.size());
    }

    @Test
    public void shouldSaveConsumer(){
        ConsumerDTO consumerDTO = ConsumerDTO
                .builder()
                .name("Consumer 1")
                .cards(new ArrayList<>())
                .build();

        Consumer consumer = Consumer.builder().id(1).name("Consumer 1").build();
        when(repository.save(any(Consumer.class))).thenReturn(consumer);
        consumerService.save(consumerDTO);

        verify(repository, times(1)).save(any());
    }
}
