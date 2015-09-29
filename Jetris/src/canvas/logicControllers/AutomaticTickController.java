package canvas.logicControllers;

import canvas.GameCanvas;

public class AutomaticTickController extends Thread {
	private GameCanvas gc;
	private long delay;
	private boolean isRunning;
	private double levelUpTimeScale;
	
	public AutomaticTickController(GameCanvas gc, int initialDelay, double levelUpTimeScale) {
		this.gc = gc;
		this.delay = initialDelay;
		this.isRunning = true;
		this.levelUpTimeScale = levelUpTimeScale;
		
		this.start();
	}
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				sleep(delay);
				JetrisEventDispatcher.tick(gc);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	
	public synchronized void stopTicker() {
		isRunning = false;
		interrupt();
	}
	
	public synchronized void resetTimer() {
		interrupt();
	}
	
	public synchronized void levelUp() {
		delay = (int) (delay * levelUpTimeScale);
		interrupt();
	}
}
