package com.core.modulecore.room;


import com.core.modulecore.common.UnexpectedException;
import com.core.modulecore.player.Player;
import com.core.modulecore.player.PlayerMismatchException;
import com.core.modulecore.util.RandomUtil;
import lombok.Getter;

import java.util.*;


public class Room {

    private final long roomId;

    private final String roomName;

    private final int capacity;

    private final RoomType roomType;

    private final Seat[] seats;

    private final Set<Player> players;


    public Room(long roomId, String roomName, int capacity, RoomType roomType) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomType = roomType;
        this.players = new HashSet<>();
        this.seats = initializeSeat(capacity);
    }


    public synchronized void joinPlayer(Player player) {
        if(!hasEmptySeat())
            throw new RoomFullException("[ERROR] 방이 꽉 차있습니다.");

        try {
            boolean added = players.add(Objects.requireNonNull(player));
            if(!added)
                throw new IllegalStateException("[ERROR] 플레이어가 이미 방에 존재합니다. roomId=%s, playerId=%s".
                        formatted(roomId, player.getPlayerId()));

            int seatNumber = findAvailableRandomSeat();
            seats[seatNumber].seat(player);
        }catch (SeatNotAvailableException e) {
            players.remove(player);
            throw e;
        }catch (IllegalArgumentException e) {
            players.remove(player);
            throw new UnexpectedException(e.getMessage(), e);
        }
    }


    public synchronized void leavePlayer(Player player) {

        try {
            boolean removed = players.remove(Objects.requireNonNull(player));
            if(!removed)
                throw new PlayerNotFoundException("[ERROR] 플레이어가 해당 방에 존재하지 않습니다. roomId=%s, playerId=%s".
                        formatted(roomId, player.getPlayerId()));
            int playerSeatNumber = getPlayerSeatNumber(player);
            seats[playerSeatNumber].leave(player);
        }catch (PlayerMismatchException | PlayerNotFoundException e) {
            players.add(player);
            throw new IllegalStateException("[ERROR] 방에 플레이어가 존재하지만, 플레이어가 앉아 있는 좌석을 찾지 못했습니다. seats=%s, playerId=%s".
                    formatted(seats, player.getPlayerId()));
        }catch (IllegalStateException | IllegalArgumentException e) {
            players.add(player);
            throw new UnexpectedException(e.getMessage(), e);
        }
    }


    public int nextTurn(int current) {

        if(players.size() < 2)
            throw new IllegalStateException("[ERROR] 플레이어가 2명 미만 입니다.");

        if(current < 0 || current >= capacity)
            throw new IllegalArgumentException("[ERROR] invalid current value. capacity=%s current=%s".
                    formatted(capacity, current));

        int seatNumber = current + 1;

        for(int i=0; i<seats.length; i++) {
            seatNumber = seatNumber >= capacity ? 0 : seatNumber;

            if(!seats[seatNumber].isEmpty())
                return seatNumber;
            seatNumber++;
        }
        throw new IllegalStateException("[ERROR] 방에 플레이어가 존재하지 않습니다.");
    }



    public int getPlayerSeatNumber(Player player) {

        for(int i=0; i<seats.length; i++) {
            Seat seat = seats[i];
            if(seat.isEmpty())
                continue;

            long playerId = seat.player.getPlayerId();

            if(player.getPlayerId() == playerId)
                return i;
        }
        throw new PlayerNotFoundException("[ERROR] 해당 플레이어가 방에 존재하지 않습니다. playerId=%s".
                formatted(player.getPlayerId()));
    }


    private int findAvailableRandomSeat() {
        int count = 0;
        int[] seatNumbers = new int[capacity];

        for(int i=0; i<seats.length; i++) {
            if(seats[i].isEmpty())
                seatNumbers[count++] = i;
        }
        if(count == 0)
            throw new RoomFullException("[ERROR] 이용가능한 좌석이 존재하지 않습니다.");
        int index = RandomUtil.getRandomInt() % count;


        return seatNumbers[index];
    }

    public boolean hasEmptySeat() {
        for(int i=0; i<seats.length; i++) {
            if(seats[i].isEmpty())
                return true;
        }
        return false;
    }


    private Seat[] initializeSeat(int maxPlayer) {
        Seat[] seats = new Seat[maxPlayer];

        for(int i=0; i<seats.length; i++) {
            seats[i] = new Seat(i, null);
        }
        return seats;
    }



    @Getter
    private class Seat {

        private int seatNumber;

        private Player player;

        public Seat(int seatNumber, Player player) {
            this.seatNumber = seatNumber;
            this.player = player;
        }

        public void seat(Player player) {
            if(player == null)
                throw new IllegalArgumentException("[ERROR] player must not be null");
            if(!isEmpty())
                throw new SeatNotAvailableException("[ERROR] 현재 좌석에 다른 플레이어가 이미 존재합니다. seatNumber=%s, seatPlayerId=%s, inputPlayerId=%s".
                        formatted(seatNumber, this.player.getPlayerId(), player.getPlayerId()));
            this.player = player;
        }

        public void leave(Player player) {
            if(player == null)
                throw new IllegalArgumentException("[ERROR] player must not be null");
            if(isEmpty())
                throw new IllegalStateException("[ERROR] 현재 좌석이 이미 비어있는 상태입니다. seatNumber=%s, playerId=%s".
                        formatted(seatNumber, player.getPlayerId()));

            if(!Objects.equals(this.player.getPlayerId(), player.getPlayerId()))
                throw new PlayerMismatchException("[ERROR] 플레이어가 일치하지 않습니다." +
                        "seatNumber=%s, seatPlayerId=%s, inputPlayerId=%s".
                                formatted(seatNumber, this.player.getPlayerId(), player.getPlayerId()));
            this.player = null;
        }

        public boolean isEmpty() {
            return this.player == null;
        }

        @Override
        public String toString() {
            return "Seat{" +
                    "seatNumber=" + seatNumber +
                    ", player=" + player +
                    '}';
        }
    }
}
