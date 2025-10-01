package com.core.modulecore.deck;

import com.core.modulecore.card.Card;

import java.security.SecureRandom;
import java.util.*;

public class DefaultShuffleStrategy implements ShuffleStrategy{


    @Override
    public Deque<Card> shuffle(List<Card> cards, SecureRandom rnd) {

        Collections.shuffle(cards, rnd);

        return new ArrayDeque<>(cards);
    }
}
