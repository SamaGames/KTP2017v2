package net.samagames.ktp2017;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class KTPProtectionListener implements Listener {

    /**
     * Protection Listener.
     * @author Vialonyx
     */

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerSwitchHands(PlayerSwapHandItemsEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
        event.setCancelled(true);
    }

}
