package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import coreGame.gameLogic.JetrisGame;
import coreGame.gameLogic.logicControllers.JetrisEventDispatcher;

public class RotationController implements ActionListener{
	private JetrisGame gc;
	private boolean clockwise;

	public RotationController(JetrisGame gc, boolean clockwise) {
		this.gc = gc;
		this.clockwise = clockwise;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JetrisEventDispatcher.rotate(gc, clockwise);
	}

}
