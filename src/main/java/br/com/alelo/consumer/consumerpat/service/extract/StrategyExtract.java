package br.com.alelo.consumer.consumerpat.service.extract;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequestDTO;

public interface StrategyExtract {
    void buy(PurchaseRequestDTO dto);
}
