package net.samagames.ktp2017.events.listeners;

import net.samagames.api.SamaGamesAPI;
import net.samagames.ktp2017.KTP2017Game;
import net.samagames.ktp2017.KTPMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

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
public class PlayerListener implements Listener {

    @EventHandler
    public void onEntityDamageByAnother(EntityDamageByEntityEvent event) {

        event.setCancelled(true);

        if (KTPMain.getInstance().getGame().getCurrentGamePhase() == KTP2017Game.GamePhase.GAME_COMBAT) {

            if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {

                Player player = (Player) event.getEntity();
                SamaGamesAPI.get().getGameManager().getCoherenceMachine().getMessageManager().writeCustomMessage(ChatColor.RED + player.getDisplayName() + ChatColor.YELLOW + " viens de mourrir", true);
                KTPMain.getInstance().getGame().eliminatePlayer(player);

            }

        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }

}
