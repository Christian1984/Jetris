package view.gui;

import input.MoveController;
import input.RotationController;
import input.StartController;
import input.TickController;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tetrominos.base.TetrominoDirection;
import view.JetrisView;
import canvas.GameCanvas;

public class ButtonController extends JPanel implements JetrisView {
	private GameCanvas gc;
	
	private JButton bMovLeft;
	private JButton bMovRight;
	private JButton bRotClockwise;
	private JButton bRotCountclockwise;
	private JButton bTick;
	private JButton bStart;
	
	public ButtonController(GameCanvas gc) {
		this.gc = gc;
		
		//setLayout(new GridLayout(0, 1, 10, 10));
		setLayout(new BorderLayout());
		
		bMovLeft = new JButton("Left");
		bMovRight = new JButton("Right");
		bRotClockwise = new JButton("Clockwise");
		bRotCountclockwise = new JButton("Counter-Clockwise");
		bTick = new JButton("Tick!");
		bStart = new JButton("Start!");
		
		JPanel p = new JPanel(new GridLayout(0, 1, 10, 10));
		
		p.add(new JLabel("Move"));
		p.add(bMovLeft);
		p.add(bMovRight);
		
		p.add(new JLabel());
		p.add(new JLabel("Rotate"));
		p.add(bRotClockwise);
		p.add(bRotCountclockwise);
		
		p.add(new JLabel());
		p.add(new JLabel("Gamemechanics"));
		p.add(bTick);
		p.add(bStart);
		
		add(p, BorderLayout.SOUTH);
		
		//TODO: Add Listeners
		bMovLeft.addActionListener(new MoveController(gc, TetrominoDirection.WEST));
		bMovRight.addActionListener(new MoveController(gc, TetrominoDirection.EAST));
		bRotClockwise.addActionListener(new RotationController(gc, true));
		bRotCountclockwise.addActionListener(new RotationController(gc, false));
		bTick.addActionListener(new TickController(gc));
		bStart.addActionListener(new StartController(gc));
	}

	@Override
	public void modelChanged() {
		bStart.setEnabled(!gc.isRunning());
		
		bTick.setEnabled(gc.isRunning());
		bMovLeft.setEnabled(gc.isRunning());
		bMovRight.setEnabled(gc.isRunning());
		bRotClockwise.setEnabled(gc.isRunning());
		bRotCountclockwise.setEnabled(gc.isRunning());
	}
}
