package com.core.modulecore.card;


import lombok.Getter;

import java.util.Objects;


@Getter
public class Card {

    private final Suit suit;

    private final Rank rank;

    private boolean hidden;

    private boolean highlighted;



    public Card(Suit suit, Rank rank) {
        this(suit, rank, false, false);
    }

    public Card(Suit suit, Rank rank, boolean hidden, boolean highlighted) {
        this.suit = suit;
        this.rank = rank;
        this.hidden = hidden;
        this.highlighted = highlighted;
    }


    public void show() {
        this.hidden = false;
    }

    public void highlight() {
        this.highlighted = true;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
