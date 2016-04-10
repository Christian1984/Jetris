package multiplayer.deprecated;

import multiplayer.ConnectionDetails;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by chris on 04.04.16.
 */
public class DeprecatedMultiplayerServiceImpl extends UnicastRemoteObject implements DeprecatedMultiplayerService {
    private ArrayList<DeprecatedJetrisGameMultiplayerImpl> games;
    private ArrayList<DeprecatedMultiplayerClient> clients;

    public DeprecatedMultiplayerServiceImpl() throws RemoteException {
        games = new ArrayList<DeprecatedJetrisGameMultiplayerImpl>();
        clients = new ArrayList<DeprecatedMultiplayerClient>();
    }

    @Override
    public synchronized DeprecatedJetrisGameMultiplayer join(DeprecatedMultiplayerClient client) throws RemoteException {
        if (!clients.contains(client)) {
            System.out.println("INFO: New client connected!");

            //add client
            clients.add(client);

            //create and add game
            DeprecatedJetrisGameMultiplayerImpl game = new DeprecatedJetrisGameMultiplayerImpl(client, this);
            games.add(game);

            //export object and return
            DeprecatedJetrisGameMultiplayer stub = (DeprecatedJetrisGameMultiplayer) UnicastRemoteObject.exportObject(game, ConnectionDetails.SERVERPORT);
            return (DeprecatedJetrisGameMultiplayer) stub;
        }

        System.out.println("WARNING: Client trying to connect is already registered!");
        return null;
    }

    @Override
    public synchronized void leave(DeprecatedMultiplayerClient client, DeprecatedJetrisGameMultiplayer game) throws RemoteException {
        //remove client
        if (clients.contains(client)) {
            clients.remove(client);
            System.out.println("INFO: Client disconnected!");
        }

        //shutdown game and remove
        if (games.contains(game)) {
            game.shutdown();
            games.remove(game);

            System.out.println("INFO: Client's game stopped and removed!");
        }
    }

    //TODO: notify clients of score-changes
    //TODO: notify Games of lines to add
    //TODO: provide method for games to notify this manager
}
