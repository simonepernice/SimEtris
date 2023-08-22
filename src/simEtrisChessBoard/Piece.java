package simEtrisChessBoard;

import java.util.Random;
import javax.microedition.lcdui.Canvas;

public class Piece extends PaintableObject {
    
    private final int PASTEL = 0x00DDDDDD;
    
    private Block block, shadow;
    
    private static Random random = new Random (System.currentTimeMillis());
    
    public Piece() {
        int type = nextRandomNumber(Block.NOOFTYPES);
        block = new Block (type, 0);
        shadow = new Block (type, PASTEL);                    
    }
    
    static public int nextRandomNumber (int max) {
        return Math.abs(random.nextInt()) % max;
    }
    
    static public void setSequence () {
        random = new Random ();
    }
    
    static public void setSequence (long seed) {
        random = new Random (seed);
    }    
    
    public void paint(javax.microedition.lcdui.Graphics g) {         
        shadow.y = block.y;
        while (shadow.move(Canvas.DOWN));
        if (shadow.y > block.y) shadow.paint(g);
        
        block.paint(g);        
    }    
    
    public void paintBlock (javax.microedition.lcdui.Graphics g) {
        block.paint(g);
    }  
    
    public boolean checkForSpace () {
        return block.checkForSpace();
    }
    
    public void split () {
        block.split();
        shadow = null; //garbage collection
    }
    
    public boolean move (int direction) {   
        shadow.y = block.y;
        switch (direction) {
            case Canvas.FIRE:
                block.rotateRight ();
                if (block.checkForSpace()) {
                    shadow.rotateRight();
                    return true;
                }
                block.rotateLeft ();
                return false;
            case Canvas.UP:
                block.rotateLeft ();
                if (block.checkForSpace()) {
                    shadow.rotateLeft();
                    return true;
                }
                block.rotateRight ();
                return false;
            case Canvas.DOWN:
                return block.move (Canvas.DOWN);
            case Canvas.LEFT:
                if (block.move (Canvas.LEFT)) {
                    shadow.move (Canvas.LEFT);
                    return true;
                }
                return false;
            case Canvas.RIGHT:
                if (block.move (Canvas.RIGHT)) {
                    shadow.move (Canvas.RIGHT);
                    return true;
                }
                return false;
            default:
                return false;
        }
    }    
    
    public void setX (int x) {
        block.x = shadow.x = x;
    }
}
