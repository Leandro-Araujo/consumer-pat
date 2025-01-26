package br.com.alelo.consumer.consumerpat.service.extract;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequestDTO;
import br.com.alelo.consumer.consumerpat.enums.CardType;

public interface StrategyExtract {
    void buy(PurchaseRequestDTO dto);
}
