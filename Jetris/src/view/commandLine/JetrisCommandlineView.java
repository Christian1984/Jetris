package view.commandLine;

import canvas.GameCanvas;
import view.JetrisView;

public class JetrisCommandlineView implements JetrisView {
	private GameCanvas gc;
	private String clearScreen;
	
	public JetrisCommandlineView(GameCanvas gc) {
		this.gc = gc;

		clearScreen = "";
		
		for (int i = 0; i < 50; i++) {
			clearScreen += "\n";
		}
		
		gc.addJetrisView(this);
	}

	@Override
	public void modelChanged() {
		System.out.println(clearScreen + gc.toString());
	}

}
