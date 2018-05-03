/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amazon.asksdk.helloworld;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Joey Crowe
 */
public class Deck {
    public ArrayList<Card> deck;

    public Deck(){
        int i,j;
        deck = new ArrayList<>();
        for (i=1;i<=4;i++){
            for (j=1;j<=13;j++){
                deck.add(new Card(j,i));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }


}
