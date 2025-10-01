package com.core.modulecore.card;

public enum HandRank {
    ROYAL_FLUSH("Royal Flush"),
    STRAIGHT_FLUSH("Straight Flush"),
    FOUR_OF_A_KIND("Four of a Kind"),
    FULL_HOUSE("Full House"),
    FLUSH("Flush"),
    STRAIGHT("Straight"),
    THREE_OF_A_KIND("Three of a Kind"),
    TWO_PAIR("Two Pair"),
    ONE_PAIR("One Pair"),
    HIGH_CARD("High Card"),
    INVALID("Invalid");


    private String category;

    HandRank(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
