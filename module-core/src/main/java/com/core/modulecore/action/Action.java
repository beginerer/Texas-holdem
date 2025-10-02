package com.core.modulecore.action;


import lombok.Getter;

@Getter
public class Action {


    private final ActionType actionType;

    private final long playerId;

    private final int betAmount;


    public Action(ActionType actionType, long playerId, int betAmount) {
        this.actionType = actionType;
        this.playerId = playerId;
        this.betAmount = betAmount;
    }


    private void validateAction(ActionType actionType, int betAmount) {
        if(actionType == ActionType.CHECK && betAmount > 0)
            throw new InvalidActionException("[ERROR] check ")

    }
}
