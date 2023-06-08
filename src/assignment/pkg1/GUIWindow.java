package assignment.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIWindow extends JFrame implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 480;
    private Color backGroundColour = new Color(4, 85, 7);
    private GamePanel gamePanel;

    public GUIWindow() {
        super("Blackjack");
        Dimension windowSize = new Dimension(WIDTH, HEIGHT);
        setSize(windowSize);
        setLocationRelativeTo(null); // put game in centre of screen
        setResizable(false); // no resize
        setBackground(backGroundColour);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Player");
        JMenuItem savePlayer = new JMenuItem("Save Player");
        JMenuItem quit = new JMenuItem("Quit");
        menu.add(savePlayer);
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // action listeners
        savePlayer.addActionListener(this);
        quit.addActionListener(this);

        try {
            gamePanel = new GamePanel();
            gamePanel.setBackground(backGroundColour);
            add(gamePanel);
        }catch (Exception e) {}

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();

        if (act.equals("Save Player"))
        {
            gamePanel.savePlayer();
        }
        if (act.equals("Quit"))
        {
            System.exit(0);
        }
    }
}
