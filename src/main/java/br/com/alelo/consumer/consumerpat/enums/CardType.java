package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
public enum CardType {
    FOOD(1, "Alimentação"),
    DRUGSTORE(2, "Farmácia"),
    FUEL(3, "Posto de combustível");

    private final Integer code;
    private final String name;

    // get mame
    public String getName() {
        return name;
    }

    // get code
    public Integer getCode() {
        return code;
    }

    public static CardType getCardType(final Integer code) {
        for (CardType type : CardType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CardType code: " + code);
    }

    public static CardType fromString(String cardTypeName) {
        for (CardType type : CardType.values()) {
            // Compara ignorando maiúsculas/minúsculas e acentos
            if (type.name.equalsIgnoreCase(cardTypeName.trim())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CardType name: " + cardTypeName);
    }

    public static CardType fromCode(Integer code) {
        for (CardType type : CardType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CardType code: " + code);
    }
}
