package assignment.pkg1;

import javax.swing.*;


public class PlayAgainPrompt extends JFrame {

    PlayAgainPrompt(Player player) {
        super();
        setSize(300, 200);
        setLocationRelativeTo(null);

        int reply = JOptionPane.showConfirmDialog(this,
                "Play another hand? (y/n) ", "Continue?",  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION)
        {
            System.out.println();
            System.out.println("You have left the table with $"+ player.getPot());
            player.saveBalance();
            System.exit(0);
        }

    }

}
