package net.samagames.ktp2017.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class GameEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private UUID winner;

    public GameEndEvent(UUID winner){
        this.winner = winner;
    }

    @Override
    public HandlerList getHandlers(){
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public UUID getWinner(){
        return this.winner;
    }

}
