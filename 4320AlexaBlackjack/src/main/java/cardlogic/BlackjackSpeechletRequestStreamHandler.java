
package cardlogic;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * This class is the handler for an AWS Lambda function powering an Alexa Skills Kit
 * experience. 
 */
public final class BlackjackSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;
    static {
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.ask.skill.ad462462-ca10-458a-9c2f-11cd9a611550");
    }

    public BlackjackSpeechletRequestStreamHandler() {
        super(new BlackjackSpeechlet(), supportedApplicationIds);
    }
}