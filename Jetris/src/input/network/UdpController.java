package input.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import coreGame.tetrominos.base.TetrominoDirection;
import coreGame.gameLogic.JetrisGame;
import coreGame.gameLogic.logicControllers.JetrisEventDispatcher;

public class UdpController extends Thread {
    private JetrisGame gc;
    DatagramSocket udpSocket;

    public static final String START = "ST";
    public static final String DOWN = "D";
    public static final String LEFT = "L";
    public static final String RIGHT = "R";
    public static final String ROT_CLOCKWISE = "C";
    public static final String ROT_COUNTERCLOCKWISE = "CC";
    public static final String QUIT = "Q";


    public UdpController(JetrisGame gc) {
        this.gc = gc;
        
        start();
    }
    
    @Override
    public void run() {        
        try {
            udpSocket = new DatagramSocket(4711);
        
            while(!interrupted()) {
                byte[] rBuffer = new byte[32];
                DatagramPacket rPacket = new DatagramPacket(rBuffer, rBuffer.length);
                
                udpSocket.receive(rPacket);
                
                String rCommand = new String(rPacket.getData()).trim();
                
                System.out.println("Command received: ==" + rCommand + "==");
                
                switch (rCommand) {
                    case START:
                        JetrisEventDispatcher.start(gc);
                        break;
                        
                    case QUIT:
                        gc.shutdown();
                        interrupt();
                        break;
                        
                    case DOWN:
                        JetrisEventDispatcher.tick(gc);
                        break;
                        
                    case LEFT:
                        JetrisEventDispatcher.move(gc, TetrominoDirection.WEST);
                        break;
                        
                    case RIGHT:
                        JetrisEventDispatcher.move(gc, TetrominoDirection.EAST);
                        break;
                        
                    case ROT_CLOCKWISE:
                        JetrisEventDispatcher.rotate(gc, true);
                        break;
                        
                    case ROT_COUNTERCLOCKWISE:
                        JetrisEventDispatcher.rotate(gc, false);
                        break;

                    default:
                        System.out.println("Unknown command!");
                        break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        udpSocket.close();
    }
}
