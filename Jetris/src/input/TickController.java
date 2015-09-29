package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import canvas.GameCanvas;
import canvas.logicControllers.JetrisEventDispatcher;

public class TickController implements ActionListener{
	private GameCanvas gc;
	
	public TickController(GameCanvas gc) {
		this.gc = gc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JetrisEventDispatcher.tick(gc);
	}

}
