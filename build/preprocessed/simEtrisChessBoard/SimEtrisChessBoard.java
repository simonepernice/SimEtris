package simEtrisChessBoard;

public class SimEtrisChessBoard extends ChessBoard {

                    
    public int checkPurge () {
        int lines = 0;
        for (int j=0; j < ROWS; ++j) {            
            lines += checkPurgeLineFromCell(cellChessBoard[0][j]);        
        }
        return lines;
    }
    
    private int checkPurgeLineFromCell (Cell a) {
        Cell next = a;
        do {
            if (next.element == null) return 0;           
            a = next;
        } while ((next = a.nextCell(Cell.RIGHT)) != null);
        clearLineOfCell (a);        
        return 1;
    }     
    
    private void clearLineOfCell (Cell a) {
        Cell first = a;
        do {
            do {
                move (a.nextCell(Cell.UP), a);
                a = a.nextCell(Cell.LEFT);
            } while (a != null);
            a = first = first.nextCell(Cell.UP);
        } while (a != null);
    }
    
}
