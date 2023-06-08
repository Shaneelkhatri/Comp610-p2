package assignment.pkg1;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameTable extends JPanel {
    public static final int CARDS_IN_PACK = 52;
    private Image[] cardImages = new Image[CARDS_IN_PACK + 1];

    // drawing position vars
    private final int CARD_INCREMENT = 20;
    private final int CARD_START = 100;
    private final int DEALER_POSITION = 50;
    private final int PLAYER_POSITION = 200;
    private final int CARD_IMAGE_WIDTH = 71;
    private final int CARD_IMAGE_HEIGHT = 96;
    private final int NAME_SPACE = 10;
    private Font handTotalFont;
    private Font playerNameFont;

    private Player player;
    private Dealer dealer;

    GameTable(Player player, Dealer dealer) {
        super();
        this.player = player;
        this.dealer = dealer;
        //------------------
        for (int i = 0; i < CARDS_IN_PACK; i++)
        {
            String cardName = "images/" + (i+1) + ".png";
            URL urlImg = getClass().getResource(cardName);
            Image cardImage = Toolkit.getDefaultToolkit().getImage(urlImg);
            cardImages[i] = cardImage;
        }
        //------------------
        this.setBackground(Color.BLUE);
        this.setOpaque(false);
        handTotalFont = new Font("Serif", Font.PLAIN, 96);
        playerNameFont = new Font("Serif", Font.ITALIC, 20);
        //------------------

    }



    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        g.setColor(Color.WHITE);

        g.setFont(playerNameFont);
        g.drawString(dealer.getName(), CARD_START, DEALER_POSITION - NAME_SPACE);
        g.drawString(player.getName(), CARD_START, PLAYER_POSITION - NAME_SPACE);
        g.setFont(handTotalFont);

        // draw dealer cards
        if(dealer.getCard1() != null && dealer.getCard2() != null){
            g.drawImage(cardImages[dealer.getCard1().getCode() - 1], CARD_START, DEALER_POSITION, this);
            g.drawImage(cardImages[dealer.getCard2().getCode() - 1], CARD_START+CARD_INCREMENT, DEALER_POSITION, this);
            g.drawString(Integer.toString(dealer.getTotal()), CARD_START + CARD_IMAGE_WIDTH + CARD_INCREMENT, DEALER_POSITION + CARD_IMAGE_HEIGHT);
        }


        // draw player cards
        if(player.getCard1() != null && player.getCard2() != null){
            g.drawImage(cardImages[player.getCard1().getCode() - 1], CARD_START, PLAYER_POSITION, this);
            g.drawImage(cardImages[player.getCard2().getCode() - 1],CARD_START+CARD_INCREMENT, PLAYER_POSITION, this);
            g.drawString(Integer.toString(player.getTotal()), CARD_START + CARD_IMAGE_WIDTH + CARD_INCREMENT, PLAYER_POSITION + CARD_IMAGE_HEIGHT);
        }
    }

}
