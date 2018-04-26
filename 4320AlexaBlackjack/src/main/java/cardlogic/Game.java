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
    public int bet;
    
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
    
    public Result deal(){
        Result result = new Result();
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
        if (checkBlackjack(playerHand) && checkBlackjack(dealerHand)){//if both player and dealer have blackjack, then tie
            result.setTie(true);
            result.setDealerBlackjack(true);
            result.setPlayerBlackjack(true);
            result.setWinMultiplier(1.0);
            
        } else if (checkBlackjack(playerHand)){
            result.setPlayerBlackjack(true);
            result.setPlayerWin(true);
            result.setWinMultiplier(2.5);
            
        } else if (checkBlackjack(dealerHand)){
            result.setDealerWin(true);
            result.setDealerBlackjack(true);
            result.setWinMultiplier(0.0);
        } else {
            //players hand to play
            result.setPlayerScore(getHandCount(playerHand));
            result.setDealerShowing(dealerHand.get(0).getHighBlackjackValue());
            result.setSoft(isSoft(playerHand));
        }
        return result;
    }
    
    public Result hit(){//every time you call for a hit, you must check to see if the dealer wins or the player busts
        Result result = new Result();
        playerHand.add(deck.get(i));
        i++;
        result.setPlayerScore(getHandCount(playerHand));
        result.setDealerShowing(dealerHand.get(0).getHighBlackjackValue());
        result.setSoft(isSoft(playerHand));
        if (getHandCount(playerHand) > 21){
            result.setPlayerBust(true);
            result.setDealerWin(true);
            result.setWinMultiplier(0.0);
        }
        return result;
    }
    
    public Result stand(){//player must have a not busted hand to stand, if stand with a busted hand, will not behave as intended
        Result result = new Result();
        int player,dealer;
        player = getHandCount(playerHand);
        result.setPlayerScore(player);
        result.setSoft(isSoft(playerHand));
        while (getHandCount(dealerHand) < 17){
            dealerHand.add(deck.get(i));
            i++;   
        }
        dealer = getHandCount(dealerHand);
        result.setDealerFinalScore(dealer);
        if (dealer > 21){
            result.setDealerBust(true);
            result.setPlayerWin(true);
            result.setWinMultiplier(2.0);
        }
        if (player > dealer){
            result.setPlayerWin(true);
            result.setWinMultiplier(2.0);
        } else if (player == dealer){
            result.setWinMultiplier(1.0);
            result.setTie(true);
        } else {
            result.setDealerWin(true);
            result.setWinMultiplier(0.0);
        }
        return result;
    }
    
    private boolean checkBlackjack(ArrayList<Card> hand){
        if (hand.size() !=2 ){
            return false;
        } else {
            int value = hand.get(0).getHighBlackjackValue()+ hand.get(1).getHighBlackjackValue();
            return value == 21;
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
            
            while (high > 21 & high > low){//i believe this should work for the entire thing, but would want to test it
                 high = high - 10;    
            }
            return high;
            
        }
    }
    
    private boolean isSoft(ArrayList<Card> hand){
        int low;
        low = 0;
        for (int j = 0; j < hand.size(); j++){
            low = low + hand.get(j).getLowBlackjackValue();
        }
        if (low == getHandCount(hand)){
            return false;
        } else {
            return true;
        }
    }
    
}
