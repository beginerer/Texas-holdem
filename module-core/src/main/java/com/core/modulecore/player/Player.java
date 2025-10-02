package com.core.modulecore.player;


import com.core.modulecore.card.Cards;
import com.core.modulecore.game.GameEvent;
import lombok.Getter;

import java.util.Objects;


@Getter
public class Player {

    private long playerId;

    private int seatNumber;

    private long chips;

    private Cards playerCards;

    private PlayerState playerState;

    private PlayerGameState playerGameState;



    public Player(long playerId, int seatNumber, long chips, Cards playerCards, PlayerState playerState, PlayerGameState playerGameState) {
        this.playerId = playerId;
        this.seatNumber = seatNumber;
        this.chips = chips;
        this.playerCards = playerCards;
        this.playerState = playerState;
        this.playerGameState = playerGameState;
    }


    public void check(GameEvent event) {
        validatePlayerActionAvailable();
        if(event)


    }

    public void bet() {

    }

    public void call() {

    }

    public void raise() {

    }

    public void allIN() {

    }

    public void fold() {

    }

    private void validatePlayerActionAvailable() {
        if(playerGameState !=  PlayerGameState.ACTIVE)
            throw new InvalidPlayerActionException("[ERROR] player game state is not active. current state=%s".
                    formatted(playerGameState));

        if(playerState != PlayerState.IN_GAME)
            throw new InvalidPlayerActionException("[ERROR} player is not in_game. current state=%s".
                    formatted(playerState));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId == player.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerId);
    }


}
