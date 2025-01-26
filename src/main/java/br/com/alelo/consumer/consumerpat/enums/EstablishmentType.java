package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstablishmentType {
    FOOD(1, "Alimentação"),
    DRUGSTORE(2, "Farmácia"),
    FUEL(3, "Posto de combustível");

    private final Integer code;
    private final String name;

    public static EstablishmentType getEstablishmentType(final Integer code) {
        for (EstablishmentType type : EstablishmentType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CompanyType code: " + code);
    }
}
