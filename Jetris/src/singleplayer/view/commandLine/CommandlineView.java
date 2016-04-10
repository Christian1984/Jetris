package singleplayer.view.commandLine;

import coreGame.gameLogic.JetrisGame;
import singleplayer.view.JetrisView;

public class CommandlineView implements JetrisView {
    private JetrisGame jetrisGame;
    private String clearScreen;

    public CommandlineView(JetrisGame jetrisGame) {
        this.jetrisGame = jetrisGame;

        clearScreen = "";

        for (int i = 0; i < 50; i++) {
            clearScreen += "\n";
        }

        jetrisGame.addJetrisView(this);
    }

    @Override
    public void modelChanged() {
        System.out.println(clearScreen + jetrisGame.toString());
    }

}
