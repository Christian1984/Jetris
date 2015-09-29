import input.network.JetrisUdpController;
import view.commandLine.JetrisCommandlineView;
import view.gui.JetrisWindow;
import canvas.GameCanvas;


public class MainCommandline {
	public static void main(String[] args) {
		GameCanvas c = new GameCanvas();
		new JetrisCommandlineView(c);
		new JetrisUdpController(c);
		
		//c.start();
	}
}
