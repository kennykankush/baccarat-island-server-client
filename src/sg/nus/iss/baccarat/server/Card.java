public class Card {

    private String rank;
    private String suit;

    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override                                  //The default toString() method would look something like:
    public String toString() {                 // public String toString() {
        return rank + suit;                    // return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }                                          // }
                                               // ClassName@1a2b3c4d                     
}                                               
                                                

