package simEtrisChessBoard;

import javax.microedition.lcdui.Graphics;

public class Translation {
    public int x;
    public int y;
    public Graphics graphics;

    public Translation (int x, int y, Graphics graphics) {
        this.x = x;
        this.y = y;
        this.graphics = graphics;
    }        
}