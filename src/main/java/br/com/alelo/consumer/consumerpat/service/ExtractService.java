package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.response.consumer.ExtractResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.extract.ExtractDrugStoreStrategy;
import br.com.alelo.consumer.consumerpat.service.extract.ExtractFoodStrategy;
import br.com.alelo.consumer.consumerpat.service.extract.ExtractFuelServiceStrategy;
import br.com.alelo.consumer.consumerpat.service.extract.StrategyExtract;
import br.com.alelo.consumer.consumerpat.util.mapping.ExtractDTOMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class ExtractService {
    private Map<CardType, StrategyExtract> strategies;


    final private ExtractFoodStrategy extractFoodStrategy;
    final private ExtractDrugStoreStrategy extractDrugStoreStrategy;
    final private ExtractFuelServiceStrategy extractFuelServiceStrategy;
    private final ExtractRepository extractRepository;

    @Autowired
    public ExtractService(ExtractFoodStrategy extractFoodStrategy,
                          ExtractDrugStoreStrategy extractDrugStoreStrategy,
                          ExtractFuelServiceStrategy extractFuelServiceStrategy,
                          ExtractRepository extractRepository){
        this.extractFoodStrategy = extractFoodStrategy;
        this.extractDrugStoreStrategy = extractDrugStoreStrategy;
        this.extractFuelServiceStrategy = extractFuelServiceStrategy;

        this.setStrategies();
        this.extractRepository = extractRepository;
    }

    private void setStrategies(){
        strategies = Map.of(
                CardType.FOOD, extractFoodStrategy,
                CardType.DRUGSTORE, extractDrugStoreStrategy,
                CardType.FUEL, extractFuelServiceStrategy
        );
    }

    public void buy(PurchaseRequestDTO purchase){
        StrategyExtract strategy = strategies.get(CardType.fromCode(purchase.getEstablishmentType()));

        strategy.buy(purchase);
    }

    public List<ExtractResponseDTO> getExtractByCardNumber(int cardNumber) {
        List<Extract> extracts = extractRepository.findByCardCardNumber(cardNumber);

        return ExtractDTOMapping.mapToDTO(extracts);
    }
}
