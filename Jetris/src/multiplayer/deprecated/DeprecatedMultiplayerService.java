package multiplayer.deprecated;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chris on 04.04.16.
 */
public interface DeprecatedMultiplayerService extends Remote {
    DeprecatedJetrisGameMultiplayer join(DeprecatedMultiplayerClient client) throws RemoteException;

    void leave(DeprecatedMultiplayerClient client, DeprecatedJetrisGameMultiplayer game) throws RemoteException;
}
