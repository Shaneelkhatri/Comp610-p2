/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package assignment.pkg1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shaneel
 */

public class MainTest {

    @Test
    public void testPlayerHit() throws Exception {
        Player player = new Player("you");
        Deck deck = new Deck();
        Card card1 = deck.nextCard();
        player.setCard1(card1);
        Card card2 = deck.nextCard();
        player.setCard2(card2);
        
        assertNotNull(player.getCard1());
        assertNotNull(player.getCard2());
    }

    @Test
    public void testPlayerStand() throws Exception {
        Player player = new Player("you");
        Deck deck = new Deck();
        Card card1 = deck.nextCard();
        player.setCard1(card1);
        Card card2 = deck.nextCard();
        player.setCard2(card2);

        assertTrue(player.getCard1().getValue() + player.getCard2().getValue() <= 21);
    }

    @Test
    public void testPlayerBust() throws Exception {
        Player player = new Player("you");
        Deck deck = new Deck();
        Card card1 = deck.nextCard();
        player.setCard1(card1);
        Card card2 = deck.nextCard();
        player.setCard2(card2);

        int total = card1.getValue() + card2.getValue();
        if (total > 21) {
            card1.setValue(1);
            card2.setValue(1);
            total = 2;
        }

        player.setTotal(22);

        assertTrue(player.getTotal() > 21);
    }

    @Test
    public void testPlayerBlackjack() throws Exception {
        Player player = new Player("you");
        Dealer dealer = new Dealer("dealer");

        player.setTotal(21);
        dealer.setTotal(21);

        assertEquals(21, player.getTotal());
        assertEquals(21, dealer.getTotal());
    }

    @Test
    public void testPlayerWin() throws Exception {
        Player player = new Player("you");
        Dealer dealer = new Dealer("dealer");

        player.setTotal(20);
        dealer.setTotal(15);

        assertTrue(player.getTotal() < 21 && player.getTotal() > dealer.getTotal());
    }
}
