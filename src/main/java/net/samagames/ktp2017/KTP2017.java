package net.samagames.ktp2017;

import net.samagames.api.games.Game;

public class KTP2017 extends Game<KTP2017Player> {

    /**
     * There is the internal representation of the Game.
     * @author Vialonyx
     */

    public KTP2017(String gameCodeName, String gameName, String gameDescription, Class<KTP2017Player> gamePlayer) {
        super(gameCodeName, gameName, gameDescription, gamePlayer);
    }

}
