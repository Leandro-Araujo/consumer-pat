package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB001_CONSUMER")
@Entity
@EqualsAndHashCode
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private int documentNumber;

    private Date birthDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

}
