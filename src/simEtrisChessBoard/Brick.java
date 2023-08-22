package simEtrisChessBoard;

public class Brick extends ChessElement {
    private int color;
    private int border;
    
    public Brick (int[] xy, int color, int border) {
        super (xy[0], xy[1]);
        this.color = color;
        this.border = border;
    }    
    
    public int getColor () {
        return color;
    }

    public void paint(javax.microedition.lcdui.Graphics g) {
        g.setColor(color);
        g.fillRect(x * XSIZE, y * YSIZE, XSIZE, YSIZE);                   
        g.setColor(border);
        g.drawRect(x * XSIZE, y * YSIZE, XSIZE, YSIZE);                   
    }
    
    public void rotateRight () {// 90� cunterclock rotatio respecto to 0,0
        int t = y;
        y = x;
        x = - t;
    }
    
    public void rotateLeft () {// 90� uncunterclock rotatio respecto to 0,0
        int t = y;
        y = - x;
        x = t;
    }
    
    public void translate (int dx, int dy) {
        x += dx;
        y += dy;
    }   
}
