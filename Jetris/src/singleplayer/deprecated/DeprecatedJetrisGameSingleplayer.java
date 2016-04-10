package singleplayer.deprecated;

import coreGame.gameLogic.JetrisGame;
import singleplayer.view.JetrisView;

import java.util.LinkedList;

/**
 * Created by chris on 05.04.16.
 */
public class DeprecatedJetrisGameSingleplayer extends JetrisGame {
    private LinkedList<JetrisView> views;

    public DeprecatedJetrisGameSingleplayer(int width, int height) {
        super(width, height);
        views = new LinkedList<JetrisView>();
    }

    public DeprecatedJetrisGameSingleplayer() {
        super();
        views = new LinkedList<JetrisView>();
    }

    //Local MVC
    protected void fireModelChanged() {
        for (JetrisView v : views) {
            v.modelChanged();
        }
    }

    public void addJetrisView(JetrisView v) {
        views.add(v);
        v.modelChanged();
    }
}
