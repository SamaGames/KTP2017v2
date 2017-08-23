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
