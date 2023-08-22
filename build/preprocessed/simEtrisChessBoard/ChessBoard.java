package simEtrisChessBoard;

import java.util.*;
import javax.microedition.lcdui.Graphics;

public class ChessBoard extends PaintableObject {
    
    public final static int ROWS = 20;      //number of rows and culumns
    public final static int CULUMNS = 11;    
    
    protected Cell[][] cellChessBoard;
        
    public ChessBoard() {
        Cell.initialize (this);
        
        cellChessBoard = new Cell[CULUMNS][ROWS];       
        
        for (int i=0; i < CULUMNS; ++i)
            for (int j=0; j < ROWS; ++j)
                cellChessBoard[i][j] = new Cell(i, j);
    }    
    
    public void paint(javax.microedition.lcdui.Graphics g) {
        g.setColor(GRAY);
        g.fillRect(0, 0, CULUMNS * XSIZE, ROWS * YSIZE);
        g.setColor(BLACK);
        g.drawRect(0, 0, CULUMNS * XSIZE, ROWS * YSIZE);
        for (int i=0; i < CULUMNS; ++i)
            for (int j=0; j < ROWS; ++j)
                cellChessBoard[i][j].paint(g);
        
    }    
    
    public void move (Cell a, Cell b) {
        if (a == null) b.element = null;
        else {
            b.element =  a.element;
            a.element = null;
        }
    }
    
    public void clearAllCells () {
        for (int i=0; i < CULUMNS; ++i)
            for (int j=0; j < ROWS; ++j)
                delCell(i, j);
    }
    
    public boolean thereIsAnElement (int x, int y) {
        if (x < 0 || x >= CULUMNS || y < 0 || y >= ROWS) return true;
        return cellChessBoard[x][y].element != null;
    }
    
    public Cell getCell (int x, int y) {
        return cellChessBoard[x][y];
    }    
    
    public void setCell (ChessElement ce) {
        cellChessBoard[ce.x][ce.y].element = ce;
        ce.reset();
    }
  
    private void delCell (int x, int y) {
        delCell (cellChessBoard[x][y]);
    }
    
    private void delCell (Cell c) {
        c.element = null;
    }                
}
