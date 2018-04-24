/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardlogic;

/**
 *
 * @author Joey Crowe
 */
public class Card {
    public int value,suit;
    
    public Card(int value, int suit){
        this.value = value;
        this.suit = suit;
    }
    
    public boolean isAce(Card card){
        return card.value == 1;
    }
}
