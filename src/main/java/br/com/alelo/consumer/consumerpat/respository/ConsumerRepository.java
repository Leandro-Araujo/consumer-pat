package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Optional<Consumer> findByCards_CardNumberAndCards_CardType(int CardNumber, CardType cardType);

    @Query(nativeQuery = true, value = "select * from TB001_CONSUMER where FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from TB001_CONSUMER where FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from TB001_CONSUMER where DRUGSTORE_NUMBER = ? ")
    Consumer findByDrugstoreNumber(int cardNumber);

}
