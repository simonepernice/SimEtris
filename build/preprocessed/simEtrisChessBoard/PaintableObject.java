package simEtrisChessBoard;

import java.util.Stack;
import javax.microedition.lcdui.*;

public abstract class PaintableObject {
    
    static int XSIZE = -1;
    static int YSIZE = -1;   
    static int XOFFSET = 0;
    static int YOFFSET = 0;
    static int YTRANSLATION = 0;
    
    private static Stack translations = new Stack ();
    
    private static final int[] XROOM = {3, 4}; // 3/4 of width for the chess board
    private static final int[] YROOM = {1, 1}; // all height for the chess board
    
    public static final int BLACK = 0X00000000;  
    public static final int GRAY =  0X00DDDDDD;  
    public static final int WHITE = 0X00FFFFFF;          
    
    public static void initializeForPaint (Canvas toPaint) {
        if (XSIZE == -1) {
            XOFFSET = toPaint.getWidth() * XROOM[0] / XROOM[1];
            if (XOFFSET < toPaint.getWidth() - TitleBar.MAXWIDTH) XOFFSET = toPaint.getWidth() - TitleBar.MAXWIDTH;
            XSIZE = XOFFSET / ChessBoard.CULUMNS;
            XOFFSET = XSIZE * ChessBoard.CULUMNS + 2;
            while (2 * XSIZE > toPaint.getWidth() - XOFFSET) {
                -- XSIZE;
                XOFFSET = XSIZE * ChessBoard.CULUMNS + 2;
            }
        }
        if (YSIZE == -1) {
            YOFFSET = toPaint.getHeight() * YROOM[0] / YROOM[1];
            YSIZE = YOFFSET / ChessBoard.ROWS;
            YTRANSLATION = (toPaint.getHeight() - YSIZE * ChessBoard.ROWS) / 2;
        }
    }    
    
    //This is called to paint this object at coordinates 0, 0
    abstract public void paint (Graphics g);      
    
    public static int getXOffset () {
        return XOFFSET;
    }
    
    public static int getYOffset () {
        return YOFFSET;
    }
    
    public static int getYTranslation () {
        return YTRANSLATION;
    }
    
    public static void trns (int x, int y, Graphics g) {
        translations.push(new Translation (x, y, g));
        g.translate(x, y);
    }
    
    public static void backtrns () {
        Translation t = (Translation) translations.pop();
        t.graphics.translate(- t.x, - t.y);
    }            
}
