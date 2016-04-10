import coreGame.gameLogic.JetrisGame;
import input.network.UdpController;
import singleplayer.view.gui.JetrisWindow;


public class LaunchSingleplayerGui {
    public static void main(String[] args) {
        JetrisGame jetrisGame = new JetrisGame();
        new JetrisWindow(jetrisGame);
        new UdpController(jetrisGame);
    }
}
