package com.core.modulecore.card;

public enum Suit {
    HEARTS("♥","h"),
    DIAMONDS("♦","d"),
    CLUBS("♣","c"),
    SPADES("♠","s"),
    HIDDEN("XX","XX");

    private final String symbol;
    private final String value;

    public static Suit[] suits = {HEARTS,DIAMONDS,CLUBS,SPADES};

    Suit(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }
}
