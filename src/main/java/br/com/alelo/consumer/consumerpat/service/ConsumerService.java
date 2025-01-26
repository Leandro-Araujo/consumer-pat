package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.response.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.mapping.ConsumerDTOMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public void save(ConsumerDTO consumerDTO){
        log.info(consumerDTO);
        Consumer consumer = ConsumerDTOMapping.toEntity(consumerDTO);
        repository.save(consumer);

        consumer.getCards().forEach(card -> {
            card.setConsumer(consumer);
            cardRepository.save(card);
        });
    }

    public List<ConsumerDTO> findAll(){
        List<Consumer> consumers = repository.findAll();
        List<ConsumerDTO> consumersDTO = ConsumerDTOMapping.toDTO(consumers);
        log.info(consumersDTO);
        return consumersDTO;
    }
}
