package com.core.modulecore.deck;

import com.core.modulecore.card.Card;
import com.core.modulecore.card.Cards;
import com.core.modulecore.card.Rank;
import com.core.modulecore.card.Suit;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;



public class Deck {

    private Cards cards;

    private int sequence;

    private final ShuffleStrategy shuffleStrategy;

    private final SecureRandom rng;


    public Deck(ShuffleStrategy shuffleStrategy) {
        this(shuffleStrategy, new SecureRandom());
    }

    public Deck(ShuffleStrategy shuffleStrategy, SecureRandom rng) {
        this.shuffleStrategy = shuffleStrategy;
        this.rng = rng;
        resetAndShuffle();
    }

    public Cards drawCards(int count) {
        Cards drawn = cards.pickCards(count);
        sequence += count;
        return drawn;
    }

    public Cards drawOne() {
        Cards drawn = cards.pickOne();
        sequence += 1;
        return drawn;
    }


    private void resetAndShuffle() {
        this.sequence = 0;
        this.cards = new Cards(makeAllCardsShuffled());
    }

    private Deque<Card> makeAllCardsShuffled() {
        List<Card> source = new ArrayList<>();

        for (Suit suit : Suit.suits) {
            for (Rank rank : Rank.ranks) {
                source.add(new Card(suit, rank));
            }
        }

        return shuffleStrategy.shuffle(source, rng);
    }

}
