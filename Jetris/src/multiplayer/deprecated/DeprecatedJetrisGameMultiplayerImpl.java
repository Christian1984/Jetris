package multiplayer.deprecated;

import coreGame.gameLogic.JetrisGame;
import multiplayer.server.RMIDispatcherServer;

import java.rmi.RemoteException;

/**
 * Created by chris on 04.04.16.
 */
public class DeprecatedJetrisGameMultiplayerImpl extends JetrisGame implements DeprecatedJetrisGameMultiplayer {
    private DeprecatedMultiplayerClient client;
    private DeprecatedMultiplayerServiceImpl service;

    protected DeprecatedJetrisGameMultiplayerImpl(DeprecatedMultiplayerClient client, DeprecatedMultiplayerServiceImpl service) throws RemoteException {
        this.client = client;
        this.service = service;
    }

    @Override
    protected int clearFullRows() {
        int clearedRows = super.clearFullRows();

        sendRows(clearedRows);

        return clearedRows;
    }

    //send rows to other players
    private void sendRows(int clearedRows) {
        //TODO: send clearedRows - 1 rows!
    }

    //receive rows from other players
    public void getRows(int rowsToAdd) {
        //TODO: add rows
    }

    //MVC via RMI
    @Override
    protected void fireModelChanged() {
        //RMIDispatcherServer.updateClient(client, this);
    }

    //Used to notify service that client lost connection
    public void clientNotReachable() {
        System.out.println("WARNING: Cannot contact client!");

        if (service != null) {
            try {
                service.leave(client, this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }






    /*@Override
    public void start() throws RemoteException {
        System.out.println("Starting new game...");
    }

    @Override
    public void shutdown() throws RemoteException {

    }

    @Override
    public void tick() throws RemoteException {

    }

    @Override
    public void rotate(boolean clockwise) throws RemoteException {

    }

    @Override
    public void left() throws RemoteException {

    }

    @Override
    public void right() throws RemoteException {

    }*/
}
