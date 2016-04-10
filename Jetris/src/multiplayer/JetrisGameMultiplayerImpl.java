package multiplayer;

import coreGame.gameLogic.JetrisGame;
import multiplayer.client.view.MultiplayerStatsView;
import multiplayer.client.RMIDispatcherClient;
import multiplayer.server.MultiplayerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by chris on 07.04.16.
 */
public class JetrisGameMultiplayerImpl extends JetrisGame implements JetrisGameMultiplayer {
    private MultiplayerService service;
    private JetrisGameMultiplayer gameStub;

    private LinkedList<MultiplayerStatsView> multiplayerStatsViews;

    public JetrisGameMultiplayerImpl() {
        multiplayerStatsViews = new LinkedList<MultiplayerStatsView>();
        //export object manually
        //(class does not inherit from UnicastRemoteObject)
        try {
            //gameStub = (JetrisGameMultiplayer) UnicastRemoteObject.exportObject(this, port);
            gameStub = (JetrisGameMultiplayer) UnicastRemoteObject.exportObject(this, 0);

            System.out.println("INFO: Object successfully exported!");
        } catch (RemoteException e) {
            //e.printStackTrace();
            System.out.println("WARNING: Cannot export object!");
        }
    }

    //manage connection to server
    public boolean join(MultiplayerService service, String playerName) {
        if (service == null) {
            System.out.println("WARNING: Cannot join. No Service available!");
            return false;
        }

        if (gameStub == null) {
            System.out.println("WARNING: Cannot join. No stub!");
            return false;
        }

        try {
            boolean success = service.join(gameStub, playerName);

            if (success) {
                System.out.println("INFO: Game joined!");
                this.service = service;

                //send initial stats
                RMIDispatcherClient.sendUpdate(service, gameStub, getScore(), getLevel(), 0);

                return true;
            } else {
                System.out.println("WARNING: Could not join game!");
                return false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void leave() {
        //TODO
    }

    //notify service when a line was cleared
    @Override
    protected int clearFullRows() {
        int clearedRows = super.clearFullRows();
        RMIDispatcherClient.sendUpdate(service, gameStub, getScore(), getLevel(), clearedRows);

        return clearedRows;
    }

    //update stats received from server
    @Override
    public synchronized void updateMultiplayerStats(ArrayList<PlayerDump> playerStats) throws RemoteException {
        fireMultiplayerStatsChanged(playerStats);
    }

    //add rows
    @Override
    public synchronized void addRows(int rows) throws RemoteException {
        shiftUp(rows);
        fillRows(rows);

        System.out.println("INFO: Added " + rows + " rows!");
    }

    //local mvc
    public synchronized void addMultiplayerStatsView(MultiplayerStatsView view) {
        multiplayerStatsViews.add(view);
    }

    public synchronized void removeMultiplayerStatsView(MultiplayerStatsView view) {
        multiplayerStatsViews.remove(view);
    }

    private void fireMultiplayerStatsChanged(ArrayList<PlayerDump> playerStats) {
        for (PlayerDump d : playerStats) {
            System.out.println(d.toString());
        }

        for (MultiplayerStatsView view : multiplayerStatsViews) {
            view.statsChanged(playerStats);
        }
    }
}
