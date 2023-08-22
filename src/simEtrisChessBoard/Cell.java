package simEtrisChessBoard;

public class Cell extends ChessElement {
    
    public PaintableObject element;    //ball conteined if != null

    protected static ChessBoard chessBoard = null;
    
    public Cell(int x, int y) {
        super (x, y);
        element = null;
    }    
 
    public void paint(javax.microedition.lcdui.Graphics g) {        
        if (element != null) {
            PaintableObject.trns(x * XSIZE, y * YSIZE, g);                        
            element.paint(g);
            PaintableObject.backtrns();
        }
        
    }  
}
