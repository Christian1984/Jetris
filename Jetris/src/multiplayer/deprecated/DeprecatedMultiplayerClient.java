package multiplayer.deprecated;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chris on 04.04.16.
 */
public interface DeprecatedMultiplayerClient extends Remote {
    void update(DeprecatedMultiplayerGamestate gameState) throws RemoteException;
}
