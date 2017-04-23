package net.samagames.ktp2017.events;

import net.samagames.ktp2017.KTPArea;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class AreaJoinEvent extends Event {

    private static HandlerList handlers = new HandlerList();
    private UUID player;
    private KTPArea area;

    public AreaJoinEvent(UUID player, KTPArea area){
        this.player = player;
        this.area = area;
    }

    @Override
    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public UUID getPlayer(){
        return this.player;
    }

    public KTPArea getArea(){
        return this.area;
    }

}
