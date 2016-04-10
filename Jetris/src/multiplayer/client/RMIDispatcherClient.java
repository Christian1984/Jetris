package multiplayer.client;

import multiplayer.JetrisGameMultiplayer;
import multiplayer.deprecated.DeprecatedMultiplayerClientImpl;
import multiplayer.deprecated.DeprecatedJetrisGameMultiplayer;
import multiplayer.server.MultiplayerService;

import java.rmi.RemoteException;

/**
 * Created by chris on 06.04.16.
 */
public class RMIDispatcherClient {
    /*public static void startGame(MultiplayerClient client, JetrisGameMultiplayer game) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    game.start();
                } catch (RemoteException e) {
                    client.gameNotReachable();
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();
    }*/

    public static void sendUpdate(MultiplayerService service, JetrisGameMultiplayer gameStub, int score, int level, int clearedRows) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    if (service != null && gameStub != null) {
                        service.update(gameStub, score, level, clearedRows);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();
    }
}
