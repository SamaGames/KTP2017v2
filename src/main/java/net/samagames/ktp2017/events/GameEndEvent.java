package net.samagames.ktp2017.events;

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
