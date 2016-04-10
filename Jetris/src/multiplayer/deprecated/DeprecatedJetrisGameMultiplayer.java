package multiplayer.deprecated;

import coreGame.block.Block;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chris on 05.04.16.
 */
public interface DeprecatedJetrisGameMultiplayer extends Remote {
    void start() throws RemoteException;

    void shutdown() throws RemoteException;

    void tick() throws RemoteException;

    void rotate(boolean clockwise) throws RemoteException;

    void left() throws RemoteException;

    void right() throws RemoteException;
}
