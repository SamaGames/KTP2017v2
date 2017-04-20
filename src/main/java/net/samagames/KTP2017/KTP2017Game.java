package net.samagames.KTP2017;

import net.samagames.api.games.Game;

public class KTP2017Game extends Game<KTPPlayer> {

    /**
     * There is the internal representation of the Game.
     * @author Vialonyx
     */

    private KTPMain instance = null;
    private GamePhase current;

    public KTP2017Game(KTPMain instance, String gameCodeName, String gameName, String gameDescription, Class<KTPPlayer> gamePlayer) {
        super(gameCodeName, gameName, gameDescription, gamePlayer);

        this.instance = instance;

        // Setting current phase to WAIT
        this.current = GamePhase.WAIT;

    }

    public static enum GamePhase {

        WAIT, AREA_STARTED, GAME_PHASE1, GAME_PHASE2, GAME_DONE;

    }

    public KTPMain getInstance(){
        return this.instance;
    }

    public GamePhase getCurrentGamePhase(){
        return this.current;
    }

}
