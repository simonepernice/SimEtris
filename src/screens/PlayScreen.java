package screens;

import javax.microedition.lcdui.*;
import java.util.Vector;
import simEtrisChessBoard.*;

public class PlayScreen extends Canvas implements CommandListener, Activable {
    
    private SimEtrisChessBoard chessBoard;
    
    private TitleBar titleBar;
    
    private Piece piece;  
    
    private boolean pause;
    
    private int challenge;
    
    private long dropTime;
    
    public PlayScreen () {
        addCommand(new Command ("BACK", Command.BACK, 0));
        setCommandListener(this);
        
        pause = true;
        
        PaintableObject.initializeForPaint(this);        
        
        chessBoard = new SimEtrisChessBoard ();
        
        titleBar = new TitleBar ();
        piece = titleBar.nextPiece();
        
        resetBoard(null);
    }
    
    private void cleanAll () {
        chessBoard.clearAllCells();
        titleBar.resetPoints();
        titleBar.nextPiece();//Important to skip the first piece for challenges (they would be different)   
        piece = titleBar.nextPiece();   
    }
    
    private void resetBoard (Object seed) {
        if (seed == null) {
            challenge = -1;
            piece.setSequence();
        } else {
            challenge = ((Integer)seed).intValue();
            piece.setSequence(challenge);
        }        
        cleanAll();
    }    
        
    public int getPoints () {
        return titleBar.getPoints();
    }
    
    public void paint(Graphics graphics) {
        graphics.setColor (PaintableObject.WHITE);
        graphics.fillRect (0, 0, getWidth(), getHeight()); 
        graphics.translate(0, PaintableObject.getYTranslation());
        chessBoard.paint (graphics);  
        piece.paint(graphics);           
        titleBar.paint (graphics);        
    }
    
    public void keyPressed (int keyCode) {
        int direction = getGameAction(keyCode);
        if (direction == Canvas.DOWN) dropTime = 50;
        if (piece.move(direction)) repaint();        
    }
    
    class BlockDropping extends Thread {        
        public void run () {
            dropTime = 1000 * (1 + TitleBar.NOFLEVELS - titleBar.getLevel()) / TitleBar.NOFLEVELS;
            
            do {                
                repaint();
                try {sleep(dropTime); } catch (Throwable t) {}                
                if (pause) return;
            } while (piece.move (Canvas.DOWN)); 
            
            synchronized (this) {//There always be a block active, the program cannot be stopped inside those operations
                piece.split();
                int lines = chessBoard.checkPurge();
                if (lines > 0) titleBar.addPoints(lines);
            
                piece = titleBar.nextPiece();
            }
            
            if (piece.checkForSpace()) new BlockDropping().start();
            else if (challenge < 0) ScreensKeeper.highScoresList.activate(Activable.TRYADDPLAYER, new Integer (titleBar.getPoints()));                 
            else {
                Alert a = new Alert ("Challenge Score", "For the challenge number: "+challenge+"\nyour score is:"+titleBar.getPoints()+"\nCompare it with your friend to find the winner!", null, AlertType.INFO);
                a.setTimeout(Alert.FOREVER);
                ScreensKeeper.display.setCurrent(a, (Displayable) ScreensKeeper.initialList);                
            }
        }
    }

    public void commandAction(Command command, Displayable displayable) {
        pause = true;
        ScreensKeeper.initialList.activate (Activable.NOTHING, null);
    }        

    public void activate(byte whatToDo, Object data) {
        switch (whatToDo) {
            case Activable.PAUSE:
                pause = true;
                break;
            case Activable.RESETBOARD:
                resetBoard(data);
                break;
            case Activable.NOTHING:
                ScreensKeeper.display.setCurrent(this);
                if (piece.checkForSpace()) {
                    pause = false;
                    new BlockDropping().start();
                }
                break;
        }
    }
}
