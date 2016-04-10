package multiplayer.deprecated;

import coreGame.gameLogic.JetrisGame;

import java.io.Serializable;

/**
 * Created by chris on 04.04.16.
 */
public class DeprecatedMultiplayerGamestate implements Serializable {
    public final String stringRepresentation;

    public DeprecatedMultiplayerGamestate(JetrisGame game) {
        stringRepresentation = game.toString();
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    //TODO
}
