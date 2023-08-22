package screens;

import javax.microedition.lcdui.*;

public class PlayerDataForm extends Form implements CommandListener, Activable {
    
    private TextField tfName;
    private StringItem siScore;
    private int score;
    
    public PlayerDataForm () {
        super ("New record!");
        
        tfName = new TextField ("Insert your name", null, 10, TextField.ANY);
        siScore = new StringItem ("Your score is", null);
        
        append (siScore);
        append (tfName);
        
        addCommand(new Command("OK", Command.BACK, 0));
        
        setCommandListener(this);
    }
    
    public void setActive (int score) {
        siScore.setText(Integer.toString(score));
        
    }

    public void commandAction(Command command, Displayable displayable) {
        if (tfName.getString().length() > 0) ScreensKeeper.highScoresList.activate(Activable.ADDPLAYER, new PlayerScore (tfName.getString(), score));
    }

    public void activate(byte whatToDo, Object data) {        
        tfName.setString("");
        score = ((Integer) data).intValue();
        siScore.setText(Integer.toString(score));
        ScreensKeeper.display.setCurrent(this);
    }                
}
