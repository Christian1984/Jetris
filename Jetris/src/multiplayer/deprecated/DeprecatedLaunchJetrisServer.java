package multiplayer.deprecated;

import multiplayer.ConnectionDetails;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by chris on 04.04.16.
 */
public class DeprecatedLaunchJetrisServer {

    public static void main(String args[]) {
        try {
            //create rmi registry
            Registry r = LocateRegistry.createRegistry(ConnectionDetails.SERVERPORT);

            //create game service and bind
            DeprecatedMultiplayerService service = new DeprecatedMultiplayerServiceImpl();
            r.rebind(ConnectionDetails.RMIOBJECTNAME, service);

            //status
            System.out.println("Server started!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
