package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tetrominos.base.TetrominoDirection;
import canvas.GameCanvas;
import canvas.logicControllers.JetrisEventDispatcher;

public class RotationController implements ActionListener{
	private GameCanvas gc;
	private boolean clockwise;
	
	public RotationController(GameCanvas gc, boolean clockwise) {
		this.gc = gc;
		this.clockwise = clockwise;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JetrisEventDispatcher.rotate(gc, clockwise);
	}

}
