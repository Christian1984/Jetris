package multiplayer.client.view;

import multiplayer.PlayerDump;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by chris on 10.04.16.
 */
public class MultiplayerStatsTableView extends JPanel implements MultiplayerStatsView {
    private JPanel dataPanel;

    public MultiplayerStatsTableView() {
        setLayout(new BorderLayout());

        dataPanel = new JPanel(new GridLayout(0, 3, 2, 2));
        add(dataPanel, BorderLayout.NORTH);
        //setLayout(new GridLayout(0, 3));
    }

    @Override
    public void statsChanged(ArrayList<PlayerDump> playersDump) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //delete old labels
                dataPanel.removeAll();

                dataPanel.add(new JLabel("Name"));
                dataPanel.add(new JLabel("Score"));
                dataPanel.add(new JLabel("Level"));

                //create and add new labels
                for (PlayerDump dump : playersDump) {

                    dataPanel.add(new JLabel(dump.getName()));
                    dataPanel.add(new JLabel("" + dump.getScore()));
                    dataPanel.add(new JLabel("" + dump.getLevel()));
                }

                //repaint();
            }
        };

        EventQueue.invokeLater(r);
    }
}
