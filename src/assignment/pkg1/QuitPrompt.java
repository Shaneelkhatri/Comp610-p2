package assignment.pkg1;

import javax.swing.*;

public class QuitPrompt extends JFrame {

    public QuitPrompt(Player player) {
        super();
        setSize(300, 200);
        setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(null, "You have no more money. Thanks for playing!");
        player.saveBalance();
        System.exit(0);
    }
}
