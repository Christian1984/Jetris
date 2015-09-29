package view.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.JetrisView;
import canvas.GameCanvas;

public class JetrisStatsView extends JPanel implements JetrisView {
	private GameCanvas gc;
	
	private JLabel lLevel;
	private JLabel lScore;
	private JLabel lLines;
	
	public JetrisStatsView(GameCanvas gc) {
		this.gc = gc;
		
		setLayout(new GridLayout(0, 1, 10, 10));
		
		lLevel = new JLabel();
		lScore = new JLabel();
		lLines = new JLabel();
		
		//JPanel p = new JPanel(new )
		add(lLevel);
		add(lScore);
		add(lLines);
	}

	@Override
	public void modelChanged() {
		lLevel.setText("Level: " + gc.getLevel());
		lScore.setText("Score: " + gc.getScore());
		lLines.setText("Rows: " + gc.getLinesCleared());
		
	}
}
