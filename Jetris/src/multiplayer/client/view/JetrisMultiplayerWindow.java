package multiplayer.client.view;

import coreGame.gameLogic.JetrisGame;
import multiplayer.JetrisGameMultiplayer;
import multiplayer.JetrisGameMultiplayerImpl;
import singleplayer.view.gui.JetrisWindow;

import java.awt.*;

/**
 * Created by chris on 10.04.16.
 */
public class JetrisMultiplayerWindow extends JetrisWindow {
    public JetrisMultiplayerWindow(JetrisGameMultiplayerImpl jetrisGame) {
        super(jetrisGame);

        MultiplayerStatsTableView statsView = new MultiplayerStatsTableView();
        jetrisGame.addMultiplayerStatsView(statsView);

        infoPanel.add(statsView, BorderLayout.CENTER);
    }
}
