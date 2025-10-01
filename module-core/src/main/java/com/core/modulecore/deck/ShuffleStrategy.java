package com.core.modulecore.deck;

import com.core.modulecore.card.Card;

import java.security.SecureRandom;
import java.util.Deque;
import java.util.List;



public interface ShuffleStrategy {

    Deque<Card> shuffle(List<Card> cards, SecureRandom rnd);
}
