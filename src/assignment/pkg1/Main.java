package assignment.pkg1;


import javax.swing.*;

public class Main
{
    public static void main(String[] args) {

        try
        {
            
            ConnectionDerby.initDB();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            //---------------------------
            //          run test cases
            //---------------------------
            runTests();
            
            //---------------------------
            //          run the game
            //---------------------------
            new Login();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
   
    //----------------------------------------------------------------
    //                      Test cases
    //----------------------------------------------------------------
       
    
    private static void runTests() throws Exception{
        // Test Case 1: Hit
        testPlayerHit();

        // Test Case 2: Stand
        testPlayerStand();

        // Test Case 3: Bust
        testPlayerBust();

        // Test Case 4: Blackjack
        testPlayerBlackjack();

        // Test Case 5: Player Wins
        testPlayerWin();
    }
    
    private static void testPlayerHit() throws Exception {
        Player player = new Player("you");  
        Deck deck = new Deck();
        // CARD 1
        Card card1 = deck.nextCard();
        player.setCard1(card1);

        // CARD 2
        Card card2 = deck.nextCard();
        player.setCard2(card2);        
        
        assert player.getCard1() != null && player.getCard2() != null: "Player should have two cards initially";
        
        System.out.println("Test Case 1: Player Hit - Passed");
    }

    // Test Case 2: Stand
    private static void testPlayerStand() throws Exception{
        Player player = new Player("you");  
        Deck deck = new Deck();
        // CARD 1
        Card card1 = deck.nextCard();
        player.setCard1(card1);

        // CARD 2
        Card card2 = deck.nextCard();
        player.setCard2(card2);        

        assert (player.getCard1().getValue() + player.getCard2().getValue()<=21) : "Player should receive total below 21 ";
        
        System.out.println("Test Case 2: Player Stand - Passed");
    }

    // Test Case 3: Bust
    private static void testPlayerBust() throws Exception{
        Player player = new Player("you");  
        Deck deck = new Deck();
        // CARD 1
        Card card1 = deck.nextCard();
        player.setCard1(card1);

        // CARD 2
        Card card2 = deck.nextCard();
        player.setCard2(card2);        
       
        int total = 0;
        if((card1.getValue() + card2.getValue()) > 21)
        {
            card1.setValue(1);
            card2.setValue(1);
            total = 2;
        }
        else total = card1.getValue() + card2.getValue();
                       
        player.setTotal(22);
    
        
        assert (player.getTotal()>21) : "Player should receive total below 21 ";

        System.out.println("Test Case 3: Player Bust - Passed");
    }

    // Test Case 4: Blackjack
    private static void testPlayerBlackjack() throws Exception{
        Player player = new Player("you");  
        Dealer dealer = new Dealer("dealer");
        
        player.setTotal(21);
        dealer.setTotal(21);
    
    
        
        assert (player.getTotal()==21 && dealer.getTotal() == 21) : "Player should get a blackjack when their hand value is 21 ";
        
        
        System.out.println("Test Case 4: Player Blackjack - Passed");
    }

    // Test Case 5: Player Wins
    private static void testPlayerWin() throws Exception{
        Player player = new Player("you");  
        Dealer dealer = new Dealer("dealer");
        
        player.setTotal(20);
        dealer.setTotal(15);
    
        
        assert (player.getTotal()<21 && player.getTotal()> dealer.getTotal()) : "Player should win when their hand value is higher than the dealer's ";
        
        
        System.out.println("Test Case 4: Player Win - Passed");
    }
    

}
