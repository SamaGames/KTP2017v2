package net.samagames.ktp2017;

import net.samagames.api.SamaGamesAPI;
import net.samagames.ktp2017.events.listeners.GameListener;
import net.samagames.ktp2017.events.listeners.KTPProtectionListener;
import net.samagames.ktp2017.events.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;
import static org.bukkit.Bukkit.getWorlds;

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
public class KTPMain extends JavaPlugin {

    private static KTPMain instance;
    private KTP2017Game game;

    @Override
    public void onEnable(){

        instance = this;

        // Registering game on SamaGamesAPI
        this.game = new KTP2017Game();
        SamaGamesAPI.get().getGameManager().setFreeMode(true);
        SamaGamesAPI.get().getGameManager().registerGame(this.game);

        // Updating gamerules values.
        getWorlds().get(0).setGameRuleValue("doDaylightCycle", "false");
        getWorlds().get(0).setTime(6000);

        // Registering event listeners.
        getServer().getPluginManager().registerEvents(new KTPProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new GameListener(this.getGame()), this);

    }

        /**
         * Get an instance of KTP2017 plugin.
         * @return an instance of KTP2017 plugin.
         */

    public static KTPMain getInstance(){
        return instance;
    }

        /**
         * Get an instance of the KTP2017 game.
         * @return an instance of KTP2017 game.
         */

    public KTP2017Game getGame(){
        return this.game;
    }

        /**
         * Get from config the amount of players which is necessary to start the game.
         * @return the amount.
         */

    public int getPlayerAmountToStart(){
        return SamaGamesAPI.get().getGameManager().getGameProperties().getOptions().get("playersToStart").getAsInt();
    }

}
