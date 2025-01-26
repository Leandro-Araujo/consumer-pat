package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String establishmentName;

    private Integer establishmentType;
}
