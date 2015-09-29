package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import canvas.GameCanvas;
import canvas.logicControllers.JetrisEventDispatcher;

public class StartController implements ActionListener{
	private GameCanvas gc;
	
	public StartController(GameCanvas gc) {
		this.gc = gc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JetrisEventDispatcher.start(gc);		
	}

}
