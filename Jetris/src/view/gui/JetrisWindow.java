package view.gui;

import input.JetrisKeyEventDispatcher;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import canvas.GameCanvas;

public class JetrisWindow extends JFrame {
	//private GameCanvas gameCanvas;
	//private JetrisGuiView guiView;
	//private ButtonController buttonContoller;
	
	public JetrisWindow(GameCanvas gameCanvas) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setSize(800, 600);
		
		this.setLayout(new BorderLayout(10, 10));
		
		JetrisGuiView mainGameView = new JetrisGuiView(gameCanvas);
		this.add(mainGameView, BorderLayout.CENTER);		
		gameCanvas.addJetrisView(mainGameView);
		
		JPanel p = new JPanel(new BorderLayout());
		this.add(p, BorderLayout.EAST);
		
		ButtonController buttonController = new ButtonController(gameCanvas);
		p.add(buttonController, BorderLayout.SOUTH);
		gameCanvas.addJetrisView(buttonController);
		
		JetrisStatsView statsView = new JetrisStatsView(gameCanvas);
		p.add(statsView, BorderLayout.NORTH);
		gameCanvas.addJetrisView(statsView);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				gameCanvas.shutdown();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//this.addKeyListener(new JetrisKeyEventDispatcher(gameCanvas));
		KeyboardFocusManager m = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		m.addKeyEventDispatcher(new JetrisKeyEventDispatcher(gameCanvas));
		
		this.setVisible(true);
	}
}
