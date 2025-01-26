package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "TB004_CARD")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private Integer cardNumber;

    private Double cardBalance;

    @Enumerated(EnumType.ORDINAL)
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "CONSUMER_ID")
    @JsonBackReference
    private Consumer consumer;

}
