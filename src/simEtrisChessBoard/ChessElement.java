package simEtrisChessBoard;

public class ChessElement extends PaintableObject {
    
    public int x, y;                 //position in the chess

    private static ChessBoard chessBoard = null;
    
    public static void initialize (ChessBoard cb) {
        if (chessBoard == null) chessBoard = cb;
    }
        
    public ChessBoard getChessBoard () {
        return chessBoard;
    }       
    
    public ChessElement(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    void reset () {
        x = y = 0;
    }

    public boolean equals (Cell a) {
        return x == a.x && y == a.y;
    }

    public void paint(javax.microedition.lcdui.Graphics g) {
        //if the cell has to have a particular shape this must be filled
    }  
    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int UPRIGHT = 4;
    public static final int UPLEFT = 5;
    public static final int DOWNRIGHT = 6;
    public static final int DOWNLEFT = 7;
    
    public Cell nextCell (int direction) {
        switch (direction) {
            case UP:
                if (y > 0) return chessBoard.getCell(x, y-1);
                break;
            case DOWN:
                if (y < chessBoard.ROWS - 1) return chessBoard.getCell(x, y+1);
                break;
            case LEFT:
                if (x > 0) return chessBoard.getCell (x-1, y);
                break;
            case RIGHT:
                if (x < chessBoard.CULUMNS - 1) return chessBoard.getCell(x+1, y);
                break;
            case UPRIGHT:
                if (y > 0 && x < chessBoard.CULUMNS - 1) return chessBoard.getCell(x+1, y-1); 
                break;
            case UPLEFT:
                if (y > 0 && x > 0) return chessBoard.getCell (x-1, y-1);
                break;
            case DOWNRIGHT:
                if (y < chessBoard.ROWS - 1 && x < chessBoard.CULUMNS - 1) return chessBoard.getCell(x+1, y+1);
                break;
            case DOWNLEFT:
                if (y < chessBoard.ROWS - 1 && x > 0) return chessBoard.getCell(x-1, y+1);
                break;
        }
        return null;
    }  
}
