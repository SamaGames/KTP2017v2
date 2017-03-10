package net.samagames.ktp2017;

import net.samagames.api.SamaGamesAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class KTP2017Main extends JavaPlugin {

    /**
     *  This is the entry point of the KTP2017 Game.
     *  @author Vialonyx
     */

    private KTP2017 game;

    @Override
    public void onEnable(){

        // Registering game on SamaGamesAPI
        this.game = new KTP2017("code", "KTP2017", "description", KTP2017Player.class);
        SamaGamesAPI.get().getGameManager().setFreeMode(true);
        SamaGamesAPI.get().getGameManager().registerGame(this.game);

    }

    public KTP2017 getGame(){
        return this.game;
    }

}
