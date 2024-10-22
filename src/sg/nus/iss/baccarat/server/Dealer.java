import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private List<Card> dealerHand = new ArrayList<>();
    Deck deck = new Deck(8);
    List<Card> shuffled = deck.shuffler(deck.getDeck());
    String dialogue = "";
    
    public List<Card> getDealerHand() {
        return dealerHand;
    }
    
    public void setDealerHand(List<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void dealCardsToPlayer(Player player){
        // System.out.println("Dealing " + 1 + " card to " + player.getHostname());
        player.playerHand.add(deck.getDeck().get(0));
        this.dialogue = player.getHostname() +  " gets " + deck.getDeck().get(0);
        deck.getDeck().remove(0);
        // System.out.println("Next card " + deck.getDeck().get(0));
        }

    public void dealCardsToDealer(){
        // System.out.println("Dealing " + 1 + " card to Dealer");
        dealerHand.add(deck.getDeck().get(0));
        this.dialogue = "Dealer gets " + deck.getDeck().get(0);
        deck.getDeck().remove(0);
        // System.out.println("Next card " + deck.getDeck().get(0));
        }
    
    public void clearHandDealer(){
        dealerHand.clear();
    }
    
}
