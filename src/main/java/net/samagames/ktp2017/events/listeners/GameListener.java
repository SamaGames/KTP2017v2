package net.samagames.ktp2017.events.listeners;

import net.samagames.api.SamaGamesAPI;
import net.samagames.ktp2017.KTP2017Game;
import net.samagames.ktp2017.KTPMain;
import net.samagames.ktp2017.Utils;
import net.samagames.ktp2017.events.AreaJoinEvent;
import net.samagames.ktp2017.events.GameEndEvent;
import net.samagames.tools.Titles;
import net.samagames.tools.chat.ActionBarAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class GameListener implements Listener {

    private KTP2017Game game;
    public GameListener(KTP2017Game game){
        this.game = game;
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event){

        this.game.updateGamePhase(KTP2017Game.GamePhase.GAME_DONE);
        this.game.getCurrentlyPlayedArea().getAreaPlayers().clear();
        this.game.setupArea(this.game.getRandomlyArea());
        SamaGamesAPI.get().getGameManager().getCoherenceMachine().getMessageManager().writeCustomMessage(ChatColor.YELLOW + "Début de la prochaine map dans " + ChatColor.RED + "5" + ChatColor.YELLOW + " secondes !", true);

        new BukkitRunnable(){

            int seconds = 6;

            @Override
            public void run(){

                seconds--;
                if(seconds == 0){

                    game.updateGamePhase(KTP2017Game.GamePhase.WAIT);
                    getOnlinePlayers().forEach(player -> game.preparePlayer(player));
                    this.cancel();
                    return;

                }

                getOnlinePlayers().forEach(player -> ActionBarAPI.sendMessage(player, "" + ChatColor.BLUE + ChatColor.BOLD + "Prochaine map dans " + ChatColor.GOLD + seconds + ChatColor.BLUE + ChatColor.BOLD + " " + Utils.formatSeconds(seconds)));

            }

        }.runTaskTimer(KTPMain.getInstance(), 0L, 20L);

    }

    @EventHandler
    public void onAreaJoin(AreaJoinEvent event){

        if(this.game.getCurrentlyPlayedArea().getAreaPlayers().size() >= KTPMain.getInstance().getPlayerAmountToStart()){

            if(this.game.getCurrentGamePhase() != KTP2017Game.GamePhase.GAME_COMBAT){
                SamaGamesAPI.get().getGameManager().getCoherenceMachine().getMessageManager().writeCustomMessage(ChatColor.GREEN + "C'est parti !", true);
                this.game.updateGamePhase(KTP2017Game.GamePhase.GAME_COMBAT);
                this.game.logDebug();
            }

        }

    }

}
