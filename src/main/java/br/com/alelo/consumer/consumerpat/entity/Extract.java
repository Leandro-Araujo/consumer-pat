package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB006_EXTRACT")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String productDescription;

    @Column
    private Date dateBuy;

    @Column(name = "EXTRACT_VALUE")
    private Double value;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "ESTABLISHMENT_ID")
    Establishment establishment;

}
