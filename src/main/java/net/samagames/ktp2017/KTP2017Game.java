package net.samagames.ktp2017;

import net.samagames.api.games.Game;
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

    public static KTPArea area1 = new KTPArea(1);
    public static KTPArea area2 = new KTPArea(2);

    public KTP2017Game(KTPMain instance, String gameCodeName, String gameName, String gameDescription, Class<KTPPlayer> gamePlayer) {
        super(gameCodeName, gameName, gameDescription, gamePlayer);

        // Initializing all the things
        this.avaibleAreas = new ArrayList<>();

        // Registering areas
        this.avaibleAreas.add(area1);
        this.avaibleAreas.add(area2);

        // Setting current phase to WAIT
        this.current = GamePhase.WAIT;

        // Debugging game variables during developement phase
        logDebug();

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

}
