package canvas.logicControllers;

import tetrominos.base.TetrominoDirection;
import canvas.GameCanvas;

public class JetrisEventDispatcher {
	public static void start(GameCanvas gc) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				gc.start();
			}
		};
		
		new Thread(r).start();
	}
	
	public static void tick(GameCanvas gc) {
		Runnable r = new Runnable() {			
			@Override
			public void run() {
				gc.tick();
			}
		};
		
		new Thread(r).start();
	}
	
	public static void move(GameCanvas gc, TetrominoDirection dir) {
		Runnable r = new Runnable() {			
			@Override
			public void run() {
				switch (dir) {
				case EAST:
					gc.right();
					break;
					
				case WEST:
					gc.left();
					break;

				default:
					break;
				}
			}
		};
		
		new Thread(r).start();
	}
	
	public static void rotate(GameCanvas gc, boolean clockwise) {
		Runnable r = new Runnable() {			
			@Override
			public void run() {
				gc.rotate(clockwise);
			}
		};
		
		new Thread(r).start();
	}
	
}
