package screens;

import java.io.IOException;
import javax.microedition.lcdui.*;
import simEtrisChessBoard.*;

public class InitialList extends List implements CommandListener, Activable {
    
    private Activable midlet;
    
    private Alert help, about, gameOver;
    private Command exit;
    
    public InitialList (Activable midlet) {
        super ("SimEtris", List.IMPLICIT);
        
        this.midlet = midlet;
        
        append ("New Game", null);
        append ("Continue last game", null);
        append ("New Challenge game", null);        
        append ("High Scores", null);
        append ("Help", null);
        append ("About", null);
        append ("Quit", null);
              
        addCommand (new Command ("SELECT", Command.OK, 0));        
        
        setCommandListener(this);
        
        ScreensKeeper.playScreen = new PlayScreen ();        
        ScreensKeeper.highScoresList = new HighScoresList ();
        ScreensKeeper.challengeForm = new ChallengeForm ();        
        ScreensKeeper.initialList = this;
        
        help = new Alert ("Help", "SimEtris is another clone of Tetris. This version uses vectorial graphics to optimize the image on different display resolutions. The goal of the game is to create complete horizontal lines of blocks, which will disappear. The blocks come in "+Block.NOOFTYPES+" different character shapes: I (red), T (gray), O (light blue), L (yellow), J (fuchsia), S (blue), Z (green). They are made by "+Block.BRIKSPERBLOCK+" bricks. The blocks fall from the top center of the screen with a random rotation and order. A preview of the next block is showed on the right of the screen and a shadow version of the current block if dropped is visible on the bottom of the screen. The game board is composed by "+SimEtrisChessBoard.ROWS+"x"+SimEtrisChessBoard.CULUMNS+" cells. You can rotate the blocks (FIRE to rotate right, UP arrow to rotate left) and move them across the screen (RIGHT and LEFT arrows) to drop (DOWN arrow) them in complete lines. After every block your score increases by the square of completed lines (if any) times the current level. Every time more than "+TitleBar.LINESPERLEVEL+" lines plus current level value are completed you go to the next level. There are "+TitleBar.NOFLEVELS+" levels: level 1 takes up to 1 second for every drop step; that time is reduced by a tenth of second for every following level. There is also a challenge mode: insert a number between 0 and 99 to get always the same sequence of blocks to challege a friend of yours! Enjoy!", null, AlertType.WARNING);
        help.setTimeout (Alert.FOREVER);
        
        Image simone=null;
        try {
            simone = Image.createImage("/Simone.png");
        } catch (IOException ioe) {}
        about = new Alert ("About", "Written for my wife Stefania.\nSimEtris is written by Simone Pernice, version 1.4.1, build 9, 29th December 2006.\nSimEtris is under GNU GPL copyleft license.\nThanks to Federico and Roman for testing and shadow suggestion.\nIf you like this game send a postcard to Simone Pernice, via Alagna 11, 10155 Torino, Italy. If you find a bug write to pernice@libero.it I will fix it! Have Fun!", simone, AlertType.WARNING);
        about.setTimeout (Alert.FOREVER);
        
        gameOver = new Alert ("Game Over", "Try again!!!", null, AlertType.WARNING);
        gameOver.setTimeout(Alert.FOREVER);
    }

    public void commandAction(Command command, Displayable displayable) {
        switch (getSelectedIndex()) {
            case 0: //new game
                ScreensKeeper.playScreen.activate(Activable.RESETBOARD, null);
            case 1: //continue last game
                ScreensKeeper.playScreen.activate(Activable.NOTHING, null);
                break;
            case 2: //New Challenge game
                ScreensKeeper.challengeForm.activate(Activable.NOTHING, null);
                break;                
            case 3: //high score
                ScreensKeeper.highScoresList.activate (Activable.NOTHING, null);
                break;
            case 4: //help
                ScreensKeeper.display.setCurrent(help, this);
                break;
            case 5: //about 
                ScreensKeeper.display.setCurrent (about, this);
                break;
            case 6: //quit
                midlet.activate(Activable.END, null);
                break;                    
        }
    }   
    
    public void activate (byte whatToDo, Object data) {
        switch (whatToDo) {
            case NOTHING:
                setSelectedIndex(1, true);
                ScreensKeeper.display.setCurrent(this);
                break;
            case GAMEOVER:
                ScreensKeeper.display.setCurrent(gameOver, this);
                break;
        }
    }
}
