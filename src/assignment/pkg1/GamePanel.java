package assignment.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.Writer;

public class GamePanel extends JPanel implements ActionListener {

    //
    private JButton hitButton = new JButton("Hit");
    private JButton standButton = new JButton("Stand");
    //
    private JLabel setBetLabel = new JLabel("Please set your bet...");
    private JButton betButton =  new JButton("Bet");
    private JTextField betTextField = new JTextField(5);
    private JLabel playerWalletLabel = new JLabel("$999.99");
    //
    private JLabel dealerSaysLabel = new JLabel("Dealer says...");
    private GameTable gameTable;

    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Shows shows;
    int bet;

    public GamePanel() throws Exception{
        this.setLayout(new BorderLayout());
        player = new Player("You");
        dealer = new Dealer("dealer");
        shows = new Shows();
        deck = new Deck();

        gameTable = new GameTable(player, dealer);
        add(gameTable, BorderLayout.CENTER);

        //setup ui
        JPanel betPanel = new JPanel();
        betPanel.add(setBetLabel);
        betPanel.add(betTextField);
        betPanel.add(betButton);
        betPanel.add(playerWalletLabel);

        JPanel dealerPanel = new JPanel();
        dealerPanel.add(dealerSaysLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.add(hitButton);
        optionsPanel.add(standButton);

        JPanel bottomItems = new JPanel();
        bottomItems.setLayout(new GridLayout(0,1));
        bottomItems.add(dealerPanel);
        bottomItems.add(betPanel);
        bottomItems.add(optionsPanel);
        add(bottomItems, BorderLayout.SOUTH);

        //action listeners
        // add listeners to buttons
        hitButton.addActionListener(this);
        standButton.addActionListener(this);
        betButton.addActionListener(this);

        //-----------------------------------------------------------------------
        FileWriter Writer = new FileWriter("output.txt");
        CheckState checkState = new CheckState();
        // variable to determine if you want to keep playing
        boolean leave = false;
        // variable to determine if player hits or stays
        String choice = "";

        System.out.println("You are invited to Play BlackJack!");
        dealerSaysLabel.setText("You are invited to Play BlackJack!");
        //Scanner scan = new Scanner(System.in);
        Writer.write("The initial funds of " + player.getName() + " is " + player.getPot() + "$\n");
        playerWalletLabel.setText(String.valueOf(player.getPot()));
        //----------------------------------------------------------------------
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        updateValues();
        
        //----------------------------------------------------------------------
    }

    public void updateValues() {
        gameTable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        if (act.equals("Hit"))
        {
            hit();
        }
        else if (act.equals("Stand"))
        {
            stand();
        }
        else if (act.equals("Bet"))
        {
            bet();
        }

        updateValues();
    }



    //------------------------------------------------------------------------------------------
    public void hit() {

        //Region - Player Turn
        System.out.println();
        Card nextCard = deck.nextCard();
        System.out.println("Your next card is: ");
        System.out.println();
        shows.printCard(nextCard);

        int total = player.getTotal();
        total += nextCard.getValue();
        player.setTotal(total);

        player.setCard1(player.getCard2());
        player.setCard2(nextCard);
        // bust scenario
        if(total > 21)
        {
            shows.printPlayerValue(player);
            System.out.println("You bust!");
            player.Loss(bet);
            System.out.println("Your pot is now $"+player.getPot());

            dealerSaysLabel.setText("You bust!");
            playerWalletLabel.setText(String.valueOf(player.getPot()));

            reset();
        }

        // move to dealer turn if total is 21
        else if(total == 21)
        {
            shows.printPlayerValue(player);
            hitButton.setEnabled(false);
            stand();
        }
        else
        {
            shows.printPlayerValue(player);
        }
        //EndRegion - Player Turn
    }

    public void stand(){
        //----------------------------------------------------//
        //Region - Dealer Turn
        //----------------------------------------------------//

        System.out.println();
        // get dealers' first two cards
        System.out.println("Dealers hand:");
        System.out.println();
        Card dcard1 = deck.nextCard();
        shows.printCard(dcard1);
        Card dcard2 = deck.nextCard();
        shows.printCard(dcard2);
        dealer.setCard1(dcard1);
        dealer.setCard2(dcard2);

        int dtotal = 0;
        if((dcard1.getValue() + dcard2.getValue()) > 21)
        {
            dcard1.setValue(1);
            dcard2.setValue(1);
            dtotal = 2;
        }
        else dtotal = dcard1.getValue() + dcard2.getValue();
        dealer.setTotal(dtotal);

        //--------------------------//
        if(dtotal == 21)
        {
            // if player total is 21, then push
            if(player.getTotal() == 21)
            {
                shows.printDealerValue(dealer);
                System.out.println("--Push--");
                dealerSaysLabel.setText("--Push--");
                reset();
            }
            // dealer blackjack scenario
            else
            {
                shows.printDealerValue(dealer);
                player.Loss(bet);
                System.out.println("The dealer has blackjack! Sorry, you lost.");
                dealerSaysLabel.setText("The dealer has blackjack! Sorry, you lost.");

                System.out.println("Your pot is now $"+player.getPot());
                playerWalletLabel.setText(String.valueOf(player.getPot()));
                //Writer.write("The pot of " + player.getName() + " is now " + player.getPot() + "$\n");
                reset();
            }
        }
        else if(dtotal > player.getTotal() && dtotal < 22)
        {
            shows.printDealerValue(dealer);
            System.out.println("Dealer Wins!");
            dealerSaysLabel.setText("Dealer Wins!");

            player.Loss(bet);
            System.out.println("Your pot is now $"+player.getPot());
            playerWalletLabel.setText(String.valueOf(player.getPot()));


            reset();
            //Writer.write("The pot of " + player.getName() + " is now " + player.getPot() + "$\n");
        }
        else if(dtotal == player.getTotal() && player.getTotal() >= 17)
        {
            System.out.println("--Push--");
            dealerSaysLabel.setText("--Push--");
            reset();
        }
        else {
            // dealer hit loop
            do{
                shows.printDealerValue(dealer);
                Card nextCard = deck.nextCard();
                System.out.println("The dealers' next card is: ");
                shows.printCard(nextCard);
                dtotal += nextCard.getValue();
                dealer.setTotal(dtotal);

                dealer.setCard1(dealer.getCard2());
                dealer.setCard2(nextCard);

                if(dtotal == 21)
                {
                    if(player.getTotal() == 21)
                    {
                        shows.printDealerValue(dealer);
                        System.out.println("--Push--");
                        dealerSaysLabel.setText("--Push--");
                        reset();
                    }
                    else
                    {
                        shows.printDealerValue(dealer);
                        player.Loss(bet);
                        System.out.println("The dealer has blackjack! Sorry, you lost.");
                        System.out.println("Your pot is now $"+player.getPot());
                        //Writer.write("The pot of " + player.getName() + " is now " + player.getPot() + "$\n");

                        dealerSaysLabel.setText("The dealer has blackjack! Sorry, you lost.");
                        playerWalletLabel.setText(String.valueOf(player.getPot()));

                        reset();
                    }
                }
                else if(dtotal > player.getTotal() && dtotal < 22)
                {
                    shows.printDealerValue(dealer);
                    System.out.println("Dealer Wins!");
                    player.Loss(bet);
                    System.out.println("Your pot is now $"+player.getPot());
                    //Writer.write("The pot of " + player.Name() + " is now " + player.Pot() + "$\n");

                    dealerSaysLabel.setText("Dealer Wins!");
                    playerWalletLabel.setText(String.valueOf(player.getPot()));

                    reset();
                }
                else if(dtotal == player.getTotal() && player.getTotal() >= 17)
                {
                    shows.printDealerValue(dealer);
                    System.out.println("--Push--");
                    dealerSaysLabel.setText("--Push--");
                    reset();
                }

            }while(dtotal < 22);
        }//else


        if(dtotal > 21)
        {
            // player blackjack scenario
            if(player.getTotal() == 21)
            {
                System.out.println("BLACKJACK!!!!!!!!!!!");
                dealerSaysLabel.setText("BLACKJACK!!!!!!!!!!!");

                player.BlackJack(bet);
                System.out.println("Your pot is now $"+player.getPot());
                playerWalletLabel.setText(String.valueOf(player.getPot()));

                //Writer.write("The pot of " + player.getName() + " is now " + player.getPot() + "$\n");
                reset();
            }
            // dealer bust scenario
            else
            {
                shows.printDealerValue(dealer);
                System.out.println("Dealer busts. You WIN!");
                dealerSaysLabel.setText("Dealer busts. You WIN!");

                player.Win(bet);

                System.out.println("Your pot is now $"+player.getPot());
                playerWalletLabel.setText(String.valueOf(player.getPot()));
                //Writer.write("The pot of " + player.getName() + " is now " + player.getPot() + "$\n");

                reset();

            }
        }
        //----------------------------------------------------//
        //EndRegion - Dealer Turn
        //----------------------------------------------------//

    }


    public void bet() {
        //---------------------------------------------------------------//
        //Region - Wager
        //---------------------------------------------------------------//
        dealerSaysLabel.setText("You are invited to Play BlackJack!");
        dealer.reset();
        player.reset();

        System.out.println("You have $" + player.getPot() + ", how much would you like to bet?");
        bet = Integer.valueOf( betTextField.getText() );

        // bet amount validation
        if(bet < 1 || bet > player.getPot())
        {
            System.out.println("Invalid amount. Enter another: ");
            dealerSaysLabel.setText("Invalid amount. Enter another: ");
            return;
        } else {
            //start the game
            System.out.println("your bet: " + bet);
            setBetLabel.setText("your bet: " + bet);
            betTextField.setEnabled(false);
            betButton.setEnabled(false);

            hitButton.setEnabled(true);
            standButton.setEnabled(true);
        }
        //---------------------------------------------------------------//
        //EndRegion - Wager
        //---------------------------------------------------------------//

        //---------------------------------------------------------------//
        //Region - Player Turn
        //---------------------------------------------------------------//

        System.out.println();

        // show players first two cards
        System.out.println("Your Hand:");
        System.out.println();

        // CARD 1
        Card card1 = deck.nextCard();
        player.setCard1(card1);
        shows.printCard(card1);

        // CARD 2
        Card card2 = deck.nextCard();
        player.setCard2(card2);
        shows.printCard(card2);
        int total = 0;

        // change ace values if two 11's are drawn
        if((card1.getValue() + card2.getValue()) > 21)
        {
            card1.setValue(1);
            card2.setValue(1);
            total = 2;
        }
        else total = card1.getValue() + card2.getValue();

        player.setTotal(total);

        // go to dealers turn if total is 21
        if(total == 21)
        {
            shows.printPlayerValue(player);
            stand();
        }
        else
        {
            shows.printPlayerValue(player);
        }

    }




    public void savePlayer() {
        player.saveBalance();
    }



    public void reset() {
        updateValues();

        if(player.getPot() == 0)
        {
            //leave = true;
            System.out.println("You have no more money. Thanks for playing!");
            //Writer.write("The pot of " + player.getName() + " is now " + player.getPot() + "$\n");
            dealerSaysLabel.setText("You have no more money. Thanks for playing!");
            playerWalletLabel.setText(String.valueOf(player.getPot()));
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            betButton.setEnabled(false);
            betTextField.setEnabled(false);

            new QuitPrompt(player);
        }else {
            //check if player want to keep playing
            new PlayAgainPrompt(player);

            //playAgainPrompt.setVisible(true);


            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            betButton.setEnabled(true);
            playerWalletLabel.setText(String.valueOf( player.getPot() ) );
            setBetLabel.setText("Please set your bet...");
            betTextField.setEnabled(true);

        }
    }
    
  
    
}
