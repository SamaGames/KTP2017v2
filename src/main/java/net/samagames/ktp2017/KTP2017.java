package net.samagames.ktp2017;

import net.samagames.api.games.Game;

public class KTP2017 extends Game<KTP2017Player> {

    /**
     * There is the internal representation of the Game.
     * @author Vialonyx
     */

    private GamePhase current;

    public KTP2017(String gameCodeName, String gameName, String gameDescription, Class<KTP2017Player> gamePlayer) {
        super(gameCodeName, gameName, gameDescription, gamePlayer);

        // Setting current phase to WAIT
        this.current = GamePhase.WAIT;

    }

    public static enum GamePhase {

        WAIT, AREA_STARTED, GAME_PHASE1, GAME_PHASE2, GAME_DONE;

    }

    public GamePhase getCurrentGamePhase(){
        return this.current;
    }

}
