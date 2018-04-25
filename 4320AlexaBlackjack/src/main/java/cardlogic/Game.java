/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardlogic;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Joey Crowe
 */
public class Game {
    public ArrayList<Card> deck;
    public ArrayList<Card> playerHand;
    public ArrayList<Card> dealerHand;
    private int i;
    
    public Game(){
       int a,b;
        deck = new ArrayList<>();
        for (a=1;a<=4;a++){
            for (b=1;b<=13;b++){
                deck.add(new Card(b,a));
            }
        }
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }
    
    public void shuffle(){
        Collections.shuffle(deck);
    }
    
    public void Deal(){
        shuffle();
        playerHand.clear();
        dealerHand.clear();
        i=0;
        playerHand.add(deck.get(i));
        i++;
        dealerHand.add(deck.get(i));
        i++;
        playerHand.add(deck.get(i));
        i++;
        dealerHand.add(deck.get(i));
        i++;
        if (checkBlackjack(playerHand) && checkBlackjack(dealerHand)){
            //push
        } else if (checkBlackjack(playerHand)){
            //player win
        } else if (checkBlackjack(dealerHand)){
            //dealer win
        } else {
            //players hand to play
        }
        
    }
    
    private boolean checkBlackjack(ArrayList<Card> hand){
        if (hand.size() !=2 ){
            return false;
        } else {
            int value = hand.get(0).getHighBlackjackValue()+ hand.get(1).getHighBlackjackValue();
            if (value == 21){
                return true;
            } else {
                return false;
            }
        }
    }
    
    private int getHandCount(ArrayList<Card> hand){
        int high,low;
        high = 0;
        low = 0;
        for (int j = 0; j < hand.size(); j++){
            high = high + hand.get(j).getHighBlackjackValue();
            low = low + hand.get(j).getLowBlackjackValue();
        }
        if (high == low) {//true if no aces
            return high;
        } else {
            if (high < 21) return high;
            else 
            
        }
    }
    
}
