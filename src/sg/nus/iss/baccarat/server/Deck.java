import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck;

    public List<Card> getDeck() {
        return deck;
    }

    public Deck(int deckQty){
        deck = new ArrayList<>();
        String[] rank = new String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] suits = new String[] {"C", "D", "H", "S"};

        for (int deckIteration = 1; deckIteration <= deckQty; deckIteration++){
            for (int i = 0; i < rank.length; i++){
                for (int j = 0; j < suits.length; j++){
                
                    Card card = new Card(rank[i],suits[j]);
                    deck.add(card);

                    if (i == rank.length && j == suits.length){

                        i = 0;
                        j = 0;
                    }
                }
            }
        }
    }

    public List<Card> shuffler(List<Card> deck){
        Collections.shuffle(deck); //https://stackoverflow.com/questions/16112515/how-to-shuffle-an-arraylist
        return deck;
    }
    
    public void whatsInMyDeck() {

        System.out.println(deck);

        System.out.println(deck.size());

    }

    
}
