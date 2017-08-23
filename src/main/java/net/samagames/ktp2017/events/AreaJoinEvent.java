package net.samagames.ktp2017.events;

import net.samagames.ktp2017.KTPArea;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/*
 * This file is part of KTP2017².
 *
 * KTP2017² is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KTP2017² is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with KTP2017².  If not, see <http://www.gnu.org/licenses/>.
 */
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
