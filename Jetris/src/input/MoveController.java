package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tetrominos.base.TetrominoDirection;
import canvas.GameCanvas;
import canvas.logicControllers.JetrisEventDispatcher;

public class MoveController implements ActionListener{
	private GameCanvas gc;
	private TetrominoDirection dir;
	
	public MoveController(GameCanvas gc, TetrominoDirection dir) {
		this.gc = gc;
		this.dir = dir;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JetrisEventDispatcher.move(gc, dir);		
	}

}
