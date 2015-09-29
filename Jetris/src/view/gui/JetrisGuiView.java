package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import view.JetrisView;
import block.Block;
import canvas.GameCanvas;

public class JetrisGuiView extends JPanel implements JetrisView {
	private GameCanvas gc;
	
	private JLabel lGameOver;
	
	int xOffset = 10;
	int yOffset = 10;
	int width = 250;
	int height = width;
	int blockSize = width;
	
	public JetrisGuiView(GameCanvas gc) {
		this.gc = gc;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		setLayout(new BorderLayout());
		
		lGameOver = new JLabel("... Game Over - Press Start to Play ...", SwingConstants.CENTER);
		//lGameOver.setForeground(Color.WHITE);
		add(lGameOver, BorderLayout.CENTER);		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		

		if (gc.isRunning()) {
			initGraphicConstraints();
			
			g.setColor(Color.darkGray);
			g.fillRect(xOffset, yOffset, width, height);
			
			/*g.setColor(Color.red);
			g.fillRect(xOffset, yOffset, blockSize, blockSize);*/
			
			if (gc.isRunning()) {
				Block[] tetroBlocks = gc.getCurrTetroBlocks();
	
				for (Block b : tetroBlocks) {
					paintBlock(b, g);
				}
				
				for (Block[] bLine : gc.getLandedBlocks()) {
					for (Block b : bLine) {
						if (b != null) {
							paintBlock(b, g);
						}
					}
				}
			}
		}
	};
	
	private void initGraphicConstraints() {
		double whRatio = (double) gc.getWidth() / gc.getHeight();
		//System.out.println("Ratio = " + whRatio);
		//System.out.println("height = " + this.getHeight() + ", width = " + this.getWidth() + ", width * ratio = " + (this.getWidth() * whRatio));
		
		if (this.getHeight() <= this.getWidth() / whRatio) {
			//height is relevant measure
			//System.out.println("Height is restricting");
			
			yOffset = this.getHeight() / 20;
			int targetHeight = this.getHeight() - 2 * yOffset;
			
			blockSize = targetHeight / gc.getHeight();
			
			height = blockSize * gc.getHeight();
			
			width = (int) (height * whRatio);
			xOffset = (this.getWidth() - width) / 2;
			
			/*yOffset = this.getHeight() / 20;
			height = this.getHeight() - 2 * yOffset;
			
			blockSize = height / gc.getHeight();
			
			System.out.println("blocksize: " + blockSize);
			
			width = (int) (height * whRatio);
			xOffset = (this.getWidth() - width) / 2;*/
			
		}
		else {
			//width is relevant measure
			//System.out.println("Width is restricting");
			
			xOffset = this.getWidth() / 20;
			int targetWidth = this.getWidth() - 2 * xOffset;
			
			blockSize = targetWidth / gc.getWidth();
			
			width = blockSize * gc.getWidth();
			
			height = (int) (width / whRatio);
			yOffset = (this.getHeight() - height) / 2;
		}
	}
	
	private void paintBlock(Block b, Graphics g) {
		if (b.inCanvas(gc.getWidth(), gc.getHeight())) {
			g.setColor(b.getColor());
			g.fillRect(xOffset + blockSize * b.getX(), yOffset + blockSize * b.getY(), blockSize, blockSize);
		}
	}
	
	@Override
	public void modelChanged() {
		lGameOver.setVisible(!gc.isRunning());		
		repaint();
	}
	
}
