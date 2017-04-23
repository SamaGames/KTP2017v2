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
