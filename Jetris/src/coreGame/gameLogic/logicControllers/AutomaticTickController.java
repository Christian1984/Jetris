package coreGame.gameLogic.logicControllers;

import coreGame.gameLogic.JetrisGame;

public class AutomaticTickController extends Thread {
	private JetrisGame jetrisGame;
	private long delay;
	private boolean isRunning;
	private double levelUpTimeScale;

	public AutomaticTickController(JetrisGame jetrisGame, int initialDelay, double levelUpTimeScale) {
		this.jetrisGame = jetrisGame;
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
				JetrisEventDispatcher.tick(jetrisGame);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch coreGame.block
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
