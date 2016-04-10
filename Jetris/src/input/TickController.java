package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import coreGame.gameLogic.JetrisGame;
import coreGame.gameLogic.logicControllers.JetrisEventDispatcher;

public class TickController implements ActionListener{
	private JetrisGame gc;

	public TickController(JetrisGame gc) {
		this.gc = gc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JetrisEventDispatcher.tick(gc);
	}

}
