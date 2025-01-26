package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
    //quero lista extratos pelo numero do cartao Extract.card.cardNumber
    List<Extract> findByCardCardNumber(int cardNumber);
}
