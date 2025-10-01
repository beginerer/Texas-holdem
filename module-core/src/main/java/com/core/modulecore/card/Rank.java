package com.core.modulecore.card;

public enum Rank {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
    SEVEN("7"), EIGHT("8"), NINE("9"), TEN("T"),
    JACK("J"), QUEEN("Q"), KING("K"), ACE("A"), HIDDEN("XX");

    private final String symbol;

    Rank(String symbol) {
        this.symbol = symbol;
    }

    public static Rank[] ranks = {TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING,ACE};

    public String getSymbol() {
        return symbol;
    }

    public static Rank fromSymbol(String symbol) {
        for (Rank value : Rank.values()) {
            if(value.getSymbol().equalsIgnoreCase(symbol))
                return value;
        }
        throw new IllegalArgumentException("[ERROR] invalid symbol.");
    }

}
