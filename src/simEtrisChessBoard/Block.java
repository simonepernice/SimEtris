package simEtrisChessBoard;

import java.util.Random;
import javax.microedition.lcdui.Canvas;

public class Block extends ChessElement {
    
    public final static int NOOFTYPES = 7;
    public final static int BRIKSPERBLOCK = 4;
    
    private Brick[] bricks;

    private static Random random = new Random (System.currentTimeMillis());
    
    private static final int[][][] BLOCKS = {   {{0,0}, {1,1}, {1,0}, {0,1}}, //O
                                                {{0,0}, {1,0}, {2,0}, {2,1}}, //J
                                                {{0,1}, {1,1}, {2,1}, {2,0}}, //L
                                                {{0,0}, {1,0}, {1,1}, {2,1}}, //Z
                                                {{0,1}, {1,1}, {1,0}, {2,0}}, //S
                                                {{0,0}, {1,0}, {2,0}, {3,0}}, //I
                                                {{0,0}, {1,0}, {2,0}, {1,1}}  //T
                                             };
    
    Block(int type, int lighten) {        
        super (0, 0);        
        
        bricks = new Brick[BRIKSPERBLOCK];
        
        for (int i = 0; i < bricks.length; ++i)
            bricks[i] = new Brick (BLOCKS[type][i], colorGenerator(type) | lighten, BLACK | lighten);
        
        rotateRight(); //if BLOCKS is well defined that is not required

    }
    
    public void paint(javax.microedition.lcdui.Graphics g) {
        for (int i = 0; i < bricks.length; ++i) {
            PaintableObject.trns(x * XSIZE, y * YSIZE, g);
            bricks[i].paint (g);
            PaintableObject.backtrns();
        } 
    }

    void rotateRight () {  
        for (int i = 0; i < bricks.length; ++i) 
            bricks[i].rotateRight();
        resetxy();
    }

    void rotateLeft () {
        for (int i = 0; i < bricks.length; ++i) 
            bricks[i].rotateLeft();
        resetxy();
    }        
    
    private void resetxy () {
        int i, minx = 0, miny = 0;
        //Find minimum x, y values
        for (i = 0; i < bricks.length; ++ i) {
            if (bricks[i].x < minx) minx = bricks[i].x;
            if (bricks[i].y < miny) miny = bricks[i].y;
        }
        //Subtract minimum x, y values (rigid translation)
        if (minx != 0 || miny != 0) 
            for (i = 0; i < bricks.length; ++ i) {
                bricks[i].x = bricks[i].x-minx;
                bricks[i].y = bricks[i].y-miny;
            }                
    }    
    
    boolean checkForSpace () {
        ChessBoard cb = getChessBoard();
        for (int i = 0; i < bricks.length; ++i) 
            if (cb.thereIsAnElement(bricks[i].x + x, bricks[i].y + y)) return false;
        return true;        
    }
    
    boolean move (int direction) {        
        int dx = 0, dy = 0;
        
        switch (direction) {
            case Canvas.FIRE:
                rotateRight ();
                if (checkForSpace()) return true;
                rotateLeft ();
                return false;
            case Canvas.UP:
                rotateLeft ();
                if (checkForSpace()) return true;
                rotateRight ();
                return false;
            case Canvas.DOWN:
                dy = 1;
                break;
            case Canvas.LEFT:
                dx = -1;
                break;
            case Canvas.RIGHT:
                dx = 1;
                break;
            default:
                return false;
        }
        
        y += dy;        
        x += dx;
        
        if (checkForSpace()) return true;
        
        y -= dy;
        x -= dx;
        return false;
    }
    
    
     void split () {
        ChessBoard cb = getChessBoard();
        for (int i = 0; i < bricks.length; ++i) {
            bricks[i].translate(x, y);
            cb.setCell (bricks[i]);        
        }
    }
                
    private static int colorGenerator (int number) {
        switch (number) {
            case 0: //O
                return 0X0000FFFF;
            case 1: //J
                return 0X00FF00FF;
            case 2: //L
                return 0X00FFFF00;
            case 3: //Z
                return 0X0000FF00;
            case 4: //S
                return 0X000000FF;
            case 5: //I
                return 0X00FF0000;
            case 6: //T
                return 0X00666666;
            default:
                return 0;
        }
    }     
}
