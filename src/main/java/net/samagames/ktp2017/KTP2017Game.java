package net.samagames.ktp2017;

import net.samagames.api.games.Game;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class KTP2017Game extends Game<KTPPlayer> {

    /**
     * There is the internal representation of the Game.
     * @author Vialonyx
     */

    private GamePhase current;
    private List<KTPArea> avaibleAreas;
    private KTPArea currentlyPlayedArea;
    private KTPArea nextPlayableArea;

    public static KTPArea area1 = new KTPArea(1);
    public static KTPArea area2 = new KTPArea(2);

    public KTP2017Game(KTPMain instance, String gameCodeName, String gameName, String gameDescription, Class<KTPPlayer> gamePlayer) {
        super(gameCodeName, gameName, gameDescription, gamePlayer);

        // Initializing all the things
        this.avaibleAreas = new ArrayList<>();

        // Registering areas
        this.avaibleAreas.add(KTP2017Game.area1);
        this.avaibleAreas.add(KTP2017Game.area2);

        // Setting current phase to WAIT
        this.current = GamePhase.WAIT;

        // Set the current playable Area
        this.currentlyPlayedArea = KTP2017Game.area1;

        // Set the next playable Area (Generated automatically and randomly in the future!)
        this.nextPlayableArea = KTP2017Game.area2;

        // Debugging game variables during developement phase
        logDebug();

    }

    @Override
    public void handleLogin(Player player){

        player.teleport(this.getCurrentlyPlayedArea().getAreaLocation());

    }

    public static enum GamePhase {

        WAIT, AREA_STARTED, GAME_PHASE1, GAME_PHASE2, GAME_DONE;

    }

    public void logDebug(){
        KTPMain.getInstance().getLogger().log(Level.INFO,"------- DEBUG -------");
        KTPMain.getInstance().getLogger().log(Level.INFO,"Current game phase : " + getCurrentGamePhase());
        KTPMain.getInstance().getLogger().log(Level.INFO,this.avaibleAreas.size() + " areas registered. " + avaibleAreas.toArray());
        KTPMain.getInstance().getLogger().log(Level.INFO,"-------- END --------");
    }

    public GamePhase getCurrentGamePhase(){
        return this.current;
    }

    public KTPArea getCurrentlyPlayedArea(){
        return this.currentlyPlayedArea;
    }

    public KTPArea getNextPlayableArea(){ return this.nextPlayableArea; }

}
