import coreGame.gameLogic.JetrisGame;
import input.network.UdpController;
import singleplayer.view.commandLine.CommandlineView;


public class LaunchSingleplayerCommandline {
    public static void main(String[] args) {
        JetrisGame jetrisGame = new JetrisGame();

        new CommandlineView(jetrisGame);
        new UdpController(jetrisGame);

        //c.start();
    }
}
