package com.core.modulecore.card;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class Cards {

    public static final Cards EMPTY = new Cards(new ArrayDeque<>(0));

    private final Deque<Card> cards;


    public Cards(Deque<Card> source) {
        this.cards = new ArrayDeque<>(requireNonNull(source, "cards must not be null"));
    }


    public Cards pickCards(int count) {
        if(count <= 0 )
            throw new InvalidCardDrawException("[ERROR] 뽑는 횟수가 0보다 커야 합니다.");
        if(cards.size() < count)
            throw new InvalidCardDrawException("[ERROR] 카드가 충분하지 않습니다.");

        Deque<Card> drawn = new ArrayDeque<>(count);

        for(int i=0; i<count; i++) {
            drawn.add(cards.removeFirst());
        }
        return new Cards(drawn);
    }

    public Cards pickOne() {
        return pickCards(1);
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }


    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }


}
