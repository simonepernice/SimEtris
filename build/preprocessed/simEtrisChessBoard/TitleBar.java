package simEtrisChessBoard;

import javax.microedition.lcdui.*;

public class TitleBar extends PaintableObject {
    
    public static final int NOFLEVELS = 10;
    public static final int LINESPERLEVEL = 9;
        
    private static final Font FONT = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    private static final int FONTHEIGHT = FONT.getHeight();    
    
    private static final String NEXT  =  "Next:";
    private static final String POINTS = "Points:";
    private static final String LEVEL =  "Level:";
    private static final String LINES =  "Lines:";
    
    public static final int MAXWIDTH = FONT.stringWidth(POINTS);
    
    private Piece next = null;
    private int points, level, lines;
    
    public TitleBar () {
        next = new Piece(); 
        resetPoints();
    }
    
    public Piece nextPiece () {
        Piece tmp = next;
        next = new Piece();
        int i = Piece.nextRandomNumber(4); //This is to give a a casual orientation of the new blocks
        for (; i >0; --i) tmp.move(Canvas.FIRE);
        tmp.setX (ChessBoard.CULUMNS/2-1);  //move the piece at the middle of the screen
        return tmp;
    }
    
    public void resetPoints () {
        points = 0;
        lines = 0;
        level = 1;
    }
    
    public void addPoints (int nOfLines) {
        lines += nOfLines;
        points += pointsIncrease (nOfLines, level);
        level += levelIncrease (points);
    }
    
    private int pointsIncrease (int lines, int level) {
        return level * lines * lines;
    }
    
    private int levelIncrease (int points) {
        if (level >= NOFLEVELS) return 0; //NOFLEVELS is the maximum level reachable
        if (lines > level * (2 * LINESPERLEVEL + level + 1) / 2) return 1;
        return 0;
    }
        
    public int getPoints () {
        return points;
    }
    
    public int getLevel () {
        return level;
    }
    
    public void paint(javax.microedition.lcdui.Graphics g) {
        PaintableObject.trns(PaintableObject.getXOffset(), 0, g);
        int y = 0;
        g.setFont(FONT);
        g.setColor(BLACK);
        g.drawString(NEXT, 0, y, 0);
        y += FONTHEIGHT;
        PaintableObject.trns(0, y, g);
        next.paintBlock(g);
        PaintableObject.backtrns();
        y += Block.BRIKSPERBLOCK * PaintableObject.YSIZE;
        g.setColor(BLACK);
        g.drawString(POINTS, 0, y, 0);
        y += FONTHEIGHT;
        g.drawString(Integer.toString(points), 0, y, 0);
        y += FONTHEIGHT;
        g.drawString(LINES, 0, y, 0);
        y += FONTHEIGHT;
        g.drawString(Integer.toString(lines), 0, y, 0);
        y += FONTHEIGHT;
        g.drawString(LEVEL, 0, y, 0);
        y += FONTHEIGHT;
        g.drawString(Integer.toString(level), 0, y, 0);    
        PaintableObject.backtrns();        
    }   
    
}
