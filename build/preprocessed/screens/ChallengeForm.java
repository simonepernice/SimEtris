package screens;

import javax.microedition.lcdui.*;

public class ChallengeForm extends Form implements CommandListener, Activable {
    
    private TextField tfChallengeNumber;
    private StringItem siExplanation;
    
    public ChallengeForm () {
        super ("Challenge");
        
        tfChallengeNumber = new TextField ("Insert challenge number", null, 2, TextField.NUMERIC);
        siExplanation = new StringItem ("This is a challenge against your friends. Insert the same challenge number and all of you will get exactly the same sequence of pieces. Only the best will win!", null);
        
        append (siExplanation);
        append (tfChallengeNumber);
        
        addCommand(new Command("OK", Command.BACK, 0));
        
        setCommandListener(this);
    }
    
    public void commandAction(Command command, Displayable displayable) {
        ScreensKeeper.playScreen.activate(Activable.RESETBOARD, new Integer (tfChallengeNumber.getString().length() == 0 ? 0 : Math.abs(Integer.parseInt(tfChallengeNumber.getString()))));
        ScreensKeeper.playScreen.activate(Activable.NOTHING, null);
    }

    public void activate(byte whatToDo, Object data) {        
        tfChallengeNumber.setString("");
        ScreensKeeper.display.setCurrent(this);
    }                
}
