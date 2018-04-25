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
    
    
    public Card(int value, int suit){//note, does not check for out of bounds cards, must make sure to properly implement in deck
        this.value = value;
        this.suit = suit;
    }
    
    public boolean isAce(Card card){
        return card.value == 1;
    }
    
    public int getHighBlackjackValue(){//again, must only have value from 1 to 13
        if (this.value == 1 ){
            return 11;
        }  else if (this.value <= 10 && this.value >= 2){
            return this.value;
        } else {
            return 10;
        }
    }
    
    public int getLowBlackjackValue(){
        if (this.value >= 1 && this.value <= 10){
            return this.value;
        } else {
            return 10;
        }
    }
}
