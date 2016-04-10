package multiplayer.deprecated;

import multiplayer.ConnectionDetails;
import multiplayer.client.RMIDispatcherClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by chris on 04.04.16.
 */
public class DeprecatedMultiplayerClientImpl extends UnicastRemoteObject implements DeprecatedMultiplayerClient {
    private DeprecatedJetrisGameMultiplayer game;
    private DeprecatedMultiplayerService service;

    public DeprecatedMultiplayerClientImpl() throws RemoteException {
        try {
            service = (DeprecatedMultiplayerService) Naming.lookup("rmi://" + ConnectionDetails.IP + ":" + ConnectionDetails.SERVERPORT + "/" + ConnectionDetails.RMIOBJECTNAME);
            System.out.println("INFO: Connected to service (lobby)!");

            game = service.join(this);

            if (game != null) {
                System.out.println("INFO: Connected to game!");
                //RMIDispatcherClient.startGame(this, game);
            } else {
                System.out.println("WARNING: Could not connect to game. Leaving...");
                service.leave(this, game);
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //MVC via RMI
    @Override
    public void update(DeprecatedMultiplayerGamestate gameState) throws RemoteException {
        System.out.println(gameState.getStringRepresentation());
    }

    public void gameNotReachable() {
        System.out.println("WARNING: Cannot contact game!");

        try {
            if (service != null) {
                service.leave(this, game);
            }
        } catch (RemoteException e) {
            serviceNotReachable();
            e.printStackTrace();
        }
    }

    public void serviceNotReachable() {
        System.out.println("WARNING: Cannot contact service!");

        try {
            if (service != null) {
                service.leave(this, game);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
