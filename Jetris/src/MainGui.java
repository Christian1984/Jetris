import input.network.JetrisUdpController;
import view.gui.JetrisWindow;
import canvas.GameCanvas;


public class MainGui {
	public static void main(String[] args) {
		GameCanvas c = new GameCanvas();
		new JetrisWindow(c);
		new JetrisUdpController(c);
	}
}
