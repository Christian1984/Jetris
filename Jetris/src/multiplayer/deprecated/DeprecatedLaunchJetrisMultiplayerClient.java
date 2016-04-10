package multiplayer.deprecated;

import multiplayer.deprecated.DeprecatedMultiplayerClientImpl;

import java.rmi.RemoteException;

/**
 * Created by chris on 04.04.16.
 */
public class DeprecatedLaunchJetrisMultiplayerClient {
    public static void main(String args[]) {
        try {
            new DeprecatedMultiplayerClientImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
