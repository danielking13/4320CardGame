/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amazon.asksdk.blackjack;

/**
 *
 * @author Joey Crowe
 */
public class Result {
    private int playerScore;
    private boolean soft; //whether the hand is hard or soft. An ace 6 is a soft 17 while an ace six and a king is a hard 17, also 7 king is hard 17
    //most hands are hard, soft is the exception
    private int dealerShowing;
    private int dealerFinalScore;
    private double winMultiplier;
    private boolean playerBlackjack;
    private boolean dealerBlackjack;
    private boolean playerBust;
    private boolean dealerBust;
    private boolean playerWin;
    private boolean dealerWin;
    private boolean tie;
    private Integer bankTotal;
    private int payout ;

    public Result(){
        soft = false;
        playerBlackjack = false;
        dealerBlackjack = false;
        playerBust = false;
        dealerBust = false;
        playerWin = false;
        dealerWin = false;
        tie = false;
        bankTotal = 5000;

    }

    public int getPayout(int bet){
        return (int) (bet * winMultiplier);
    }

    /**
     * @return the playerScore
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * @param playerScore the playerScore to set
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * @return the soft
     */
    public boolean isSoft() {
        return soft;
    }

    /**
     * @param soft the soft to set
     */
    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    /**
     * @return the dealerShowing
     */
    public int getDealerShowing() {
        return dealerShowing;
    }

    /**
     * @param dealerShowing the dealerShowing to set
     */
    public void setDealerShowing(int dealerShowing) {
        this.dealerShowing = dealerShowing;
    }

    /**
     * @return the dealerFinalScore
     */
    public int getDealerFinalScore() {
        return dealerFinalScore;
    }

    /**
     * @param dealerFinalScore the dealerFinalScore to set
     */
    public void setDealerFinalScore(int dealerFinalScore) {
        this.dealerFinalScore = dealerFinalScore;
    }

    /**
     * @return the winMultiplier
     */
    public double getWinMultiplier() {
        return winMultiplier;
    }

    /**
     * @param winMultiplier the winMultiplier to set
     */
    public void setWinMultiplier(double winMultiplier) {
        this.winMultiplier = winMultiplier;
    }

    /**
     * @return the blackjack
     */
    public boolean isPlayerBlackjack() {
        return playerBlackjack;
    }

    /**
     * @param blackjack the blackjack to set
     */
    public void setPlayerBlackjack(boolean playerBlackjack) {
        this.playerBlackjack = playerBlackjack;
    }

    /**
     * @return the playerBust
     */
    public boolean isPlayerBust() {
        return playerBust;
    }

    /**
     * @param playerBust the playerBust to set
     */
    public void setPlayerBust(boolean playerBust) {
        this.playerBust = playerBust;
    }

    /**
     * @return the playerWin
     */
    public boolean isPlayerWin() {
        return playerWin;
    }

    /**
     * @param playerWin the playerWin to set
     */
    public void setPlayerWin(boolean playerWin) {
        this.playerWin = playerWin;
    }

    /**
     * @return the dealerWin
     */
    public boolean isDealerWin() {
        return dealerWin;
    }

    /**
     * @param dealerWin the dealerWin to set
     */
    public void setDealerWin(boolean dealerWin) {
        this.dealerWin = dealerWin;
    }

    /**
     * @return the tie
     */
    public boolean isTie() {
        return tie;
    }

    /**
     * @param tie the tie to set
     */
    public void setTie(boolean tie) {
        this.tie = tie;
    }

    /**
     * @return the dealerBlackjack
     */
    public boolean isDealerBlackjack() {
        return dealerBlackjack;
    }

    /**
     * @param dealerBlackjack the dealerBlackjack to set
     */
    public void setDealerBlackjack(boolean dealerBlackjack) {
        this.dealerBlackjack = dealerBlackjack;
    }

    /**
     * @return the dealerBust
     */
    public boolean isDealerBust() {
        return dealerBust;
    }

    /**
     * @param dealerBust the dealerBust to set
     */
    public void setDealerBust(boolean dealerBust) {
        this.dealerBust = dealerBust;
    }
    public void updateBankTotal (int payout) {
//        Shouldn't this be += payout?
        this.bankTotal += bankTotal;
    }

    public int getBank () {
        return bankTotal;
    }

}
