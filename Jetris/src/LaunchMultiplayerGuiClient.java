import coreGame.gameLogic.JetrisGame;
import input.network.UdpController;
import multiplayer.ConnectionDetails;
import multiplayer.JetrisGameMultiplayerImpl;
import multiplayer.client.view.JetrisMultiplayerWindow;
import multiplayer.server.MultiplayerService;
import singleplayer.view.gui.JetrisWindow;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by chris on 07.04.16.
 */
public class LaunchMultiplayerGuiClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments. Required:");
            System.out.println("\t- Server-Address (IP)");
            System.out.println("\t- Player's name");

            System.exit(-1);
        }

        String serverAddress = args[0];
        String name = args[1];

        System.out.println("Joining game...");

        MultiplayerService service = getService(serverAddress);

        if (service == null) {
            System.out.println("Could not connect to service. Quitting...");
            System.exit(-1);
        }

        JetrisGameMultiplayerImpl jetrisGame = new JetrisGameMultiplayerImpl();

        jetrisGame.join(service, name);

        new JetrisMultiplayerWindow(jetrisGame);
        //new UdpController(jetrisGame);
    }

    public static MultiplayerService getService(String serverAddress) {
        try {
            MultiplayerService service = (MultiplayerService) Naming.lookup("rmi://" + serverAddress + ":" + ConnectionDetails.SERVERPORT + "/" + ConnectionDetails.RMIOBJECTNAME);
            System.out.println("INFO: Service found!");

            return service;
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
