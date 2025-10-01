package com.core.modulecore.game;

import com.core.modulecore.Pot.Pot;
import com.core.modulecore.card.Cards;
import com.core.modulecore.player.Player;

import java.time.LocalDateTime;
import java.util.List;

public class Game {

    private long gameId;

    private long sequence;

    private Pot pot;

    private List<Player> players;

    private Round round;

    private Cards communityCards;


    private GameState gameState;

    private LocalDateTime startTime;

    private LocalDateTime endTime;




}
