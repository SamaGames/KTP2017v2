package net.samagames.ktp2017;

import com.google.gson.JsonObject;
import net.samagames.api.SamaGamesAPI;
import net.samagames.ktp2017.events.listeners.GameListener;
import net.samagames.ktp2017.events.listeners.KTPProtectionListener;
import net.samagames.ktp2017.events.listeners.PlayerListener;
import net.samagames.tools.LocationUtils;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static org.bukkit.Bukkit.getWorld;
import static org.bukkit.Bukkit.getWorlds;

public class KTPMain extends JavaPlugin {

    /**
     *  This is the entry point of the KTP2017Game Game.
     *  @author Vialonyx
     */

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

        getLogger().log(Level.INFO, "Players need to start : " + getPlayerAmountToStart());

    }

    public static KTPMain getInstance(){
        return instance;
    }

    public KTP2017Game getGame(){
        return this.game;
    }

    public int getPlayerAmountToStart(){
        return SamaGamesAPI.get().getGameManager().getGameProperties().getOptions().get("playersToStart").getAsInt();
    }

}
