package assignment.pkg1;

public class Dealer {
    private String name;
    private Card card1;
    private Card card2;
    private int total;

    public Dealer(String name) {
        this.name = name;
    }


    public Card getCard1() {
        return card1;
    }
    public void setCard1(Card card1) {
        this.card1 = card1;
    }
    public Card getCard2() {
        return card2;
    }
    public void setCard2(Card card2) {
        this.card2 = card2;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void reset() { card1 = null; card2 = null; total = 0; }
}
