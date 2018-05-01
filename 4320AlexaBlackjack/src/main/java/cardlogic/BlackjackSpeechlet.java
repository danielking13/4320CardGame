/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardlogic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.OutputSpeech;
/**
 *
 * @author danielking
 */
public class BlackjackSpeechlet implements SpeechletV2 {
    private static final Logger log = LoggerFactory.getLogger(BlackjackSpeechlet.class);

    
    Game game = new Game();
    
    
    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        log.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
        // any initialization logic goes here
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        log.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
        return getNewFactResponse(); //This needs to be Kolton's method to start new game onLaunch
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        IntentRequest request = requestEnvelope.getRequest();
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                requestEnvelope.getSession().getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("CardTotalIntent".equals(intentName)) {
            return getCardTotalResponse();

        } else if("WinOrBustIntent".equals(intentName)){
            return getWinOrBustResponse();
            
        } else if("ReadPlayersHandIntent".equals(intentName)){
            return readPlayersHand();
        
        } else if("HitIntent".equals(intentName)){
            return hitHand();
        
        } else if("StandIntent".equals(intentName)){
            return standHand();
        
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();

        } else if ("AMAZON.StopIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else {
            return getAskResponse("Blackjack", "This is unsupported.  Please try something else.");
        }
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        log.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
        // any cleanup logic goes here
    }
    
    /**
     * Returns the card total after a round.
    */
    private SpeechletResponse getCardTotalResponse() {
        int cardTotal = (int)8;
        
        String speechText = "My hitter my hitter. Your card total is " + cardTotal;
        
        SimpleCard card = getSimpleCard("CardTotal", speechText);
        
        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        
        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    private SpeechletResponse getWinOrBustResponse() {
        boolean winOrBust = true;
        String speechText;
        
        if(winOrBust){
            speechText = "Congratulations, you have won!";
        }
        else {
            speechText = "Bummer, you busted.";
        }
        
        SimpleCard card = getSimpleCard("WinOrBust", speechText);
        
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    private SpeechletResponse standHand() {
        
        String speechText;
        int i = 0;
        
        if(game.stand().dealerBust){
        speechText = "Your total is a" + game.getHandCount(playerHand) + "I hit until I busted. You win!";
        } else {
            if(###playerWins###) {
                speechText = "Your total is a" + game.getHandCount(playerHand) + "I hit until my score was a" 
                + game.getHandCount(dealerHand) + "You win!";
            } else {
                speechText = "Your total is a" + game.getHandCount(playerHand) + "I hit until my score was a" 
                + game.getHandCount(dealerHand) + "I win!";
            }
        }
        
        SimpleCard card = getSimpleCard("HitHand", speechText);
        
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    
    private SpeechletResponse hitHand() {
        
        String speechText;
        int i = 0; //static?
        
        if(game.hit().playerBust) {
            speechText = "You busted. You lose."
        } else {
            speechText = "You were dealt a" + game.playerHand.get(i).value + "so your total is now a" 
                        + game.getHandCount(playerHand) + "Would you like to hit or stand?";
        }
        
        SimpleCard card = getSimpleCard("HitHand", speechText);
        
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    private SpeechletResponse readPlayersHand() {
        //Game game = new Game();
        game.deal();
        
        String speechText;
        int i = 0;
        
        speechText = "You were dealt a" + game.playerHand.get(i).value + "and a" + game.playerHand.get(i+1).value
                        + "Your hand is a" + game.getHandCount(playerHand) + "Would you like to hit or stand?";
        
        SimpleCard card = getSimpleCard("PlayerHand", speechText);
        
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    
    
    private SpeechletResponse getEndGameResponse() {
        //take this logic and put it somewhere else!
//         Game game = new Game();
//         Result result = game.hit();
//         if(result.getBankTotal() <= 0) {
//             return getEndGameResponse();
//         }
//alex
        // Create speech output
//        if(result.getBankTotal() <= 0) {
//            String speechText = "You have run out of money. Would you like to start a new game?";
//        }
        String speechText = "Would you like to start a new game?";

        // Create the Simple card content.
        SimpleCard card = getSimpleCard("EndGame", speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    /**
     * Returns a response for the help intent.
     */
    private SpeechletResponse getHelpResponse() {
        String speechText =
                "You can ask Blackjack to deal, ask for how to play, or, you can say exit. What can I "
                        + "help you with?";
        return getAskResponse("Blackjack", speechText);
    }

    /**
     * Helper method that creates a card object.
     * @param title title of the card
     * @param content body of the card
     * @return SimpleCard the display card to be sent along with the voice response.
     */
    private SimpleCard getSimpleCard(String title, String content) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);

        return card;
    }

    /**
     * Helper method for retrieving an OutputSpeech object when given a string of TTS.
     * @param speechText the text that should be spoken out to the user.
     * @return an instance of SpeechOutput.
     */
    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return speech;
    }

    /**
     * Helper method that returns a reprompt object. This is used in Ask responses where you want
     * the user to be able to respond to your speech.
     * @param outputSpeech The OutputSpeech object that will be said once and repeated if necessary.
     * @return Reprompt instance.
     */
    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);

        return reprompt;
    }

    /**
     * Helper method for retrieving an Ask response with a simple card and reprompt included.
     * @param cardTitle Title of the card that you want displayed.
     * @param speechText speech text that will be spoken to the user.
     * @return the resulting card and speech text.
     */
    private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        SimpleCard card = getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}
