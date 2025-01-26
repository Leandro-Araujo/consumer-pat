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
public class ExtractFoodStrategy implements StrategyExtract{
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private ExtractRepository extractRepository;
    @Override
    @Transactional
    public void buy(PurchaseRequestDTO purchase) {
        Card card = cardRepository
                .findCardByCardNumber(purchase.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado."));

        if (card.getCardType().getCode() != CardType.FOOD.getCode()) {
            throw new RuntimeException("Tipo de cartão incompatível com o tipo do estabelecimento");
        }

        if (purchase.getValue() > card.getCardBalance()) {
            throw new RuntimeException("Saldo insuficiente");
        }

        Double cashback = purchase.getValue() * 0.1;
        Double value = purchase.getValue() - cashback;
        purchase.setValue(value);

        card.setCardBalance(card.getCardBalance() - (purchase.getValue()));
        cardRepository.save(card);

        Establishment establishment = new Establishment();
        establishment.setEstablishmentType(purchase.getEstablishmentType());
        establishment.setEstablishmentName(purchase.getEstablishmentName());
        establishmentRepository.save(establishment);

        Extract extract = new Extract();
        extract.setCard(card);
        extract.setDateBuy(new Date());
        extract.setProductDescription(purchase.getProductDescription());
        extract.setValue(purchase.getValue());
        extract.setEstablishment(establishment);
        extractRepository.save(extract);
    }
}
