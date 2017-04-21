package net.samagames.ktp2017.listeners;

import net.samagames.api.SamaGamesAPI;
import net.samagames.ktp2017.KTP2017Game;
import net.samagames.ktp2017.KTPMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (KTPMain.getInstance().getGame().getCurrentGamePhase() == KTP2017Game.GamePhase.GAME_PHASE2) {

            if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {

                event.setCancelled(true);
                Player player = (Player) event.getEntity();
                SamaGamesAPI.get().getGameManager().getCoherenceMachine().getMessageManager().writeCustomMessage(ChatColor.RED + player.getDisplayName() + ChatColor.YELLOW + " viens de mourrir", true);
                KTPMain.getInstance().getGame().eliminatePlayer(player.getUniqueId());

            }

        }
    }

}
