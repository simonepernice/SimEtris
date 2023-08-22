package screens;

public interface Activable {
    
    public final byte NOTHING = 0;
    public final byte RESETBOARD = 1;
    public final byte ADDPLAYER = 2;
    public final byte TRYADDPLAYER = 3;
    public final byte GAMEOVER = 4;
    public final byte END = 5;
    public final byte PAUSE = 6;
    
    //This is to activate a screen
    public void activate (byte whatToDo, Object data);
}
