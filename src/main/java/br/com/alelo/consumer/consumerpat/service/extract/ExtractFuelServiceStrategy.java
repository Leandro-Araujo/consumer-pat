package br.com.alelo.consumer.consumerpat.service.extract;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequestDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ExtractFuelServiceStrategy implements StrategyExtract{
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private ExtractRepository extractRepository;

    @Override
    @Transactional
    public void buy(PurchaseRequestDTO dto) {
        Card card = cardRepository
                .findCardByCardNumber(dto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado."));

        if (card.getCardType().getCode() != CardType.FUEL.getCode()) {
            throw new RuntimeException("Tipo de cartão inválido");
        }

        if (dto.getValue() > card.getCardBalance()) {
            throw new RuntimeException("Saldo insuficiente");
        }

        Double tax = dto.getValue() * 0.35;
        dto.setValue(dto.getValue() + tax);

        card.setCardBalance(card.getCardBalance() - dto.getValue());
        cardRepository.save(card);
        Establishment establishment = new Establishment();
        establishment.setEstablishmentType(dto.getEstablishmentType());
        establishment.setEstablishmentName(dto.getEstablishmentName());
        establishmentRepository.save(establishment);

        Extract extract = new Extract();
        extract.setCard(card);
        extract.setDateBuy(new Date());
        extract.setValue(dto.getValue());
        extract.setProductDescription(dto.getProductDescription());
        extract.setEstablishment(establishment);
        extractRepository.save(extract);
    }
}
