import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import screens.*;

public class SimEtris extends MIDlet implements Activable {

    private InitialList initialList = null;
    private static long timer;
    
    public void startApp() {
        if (initialList == null) {
            ScreensKeeper.display = Display.getDisplay(this);
            initialList = new InitialList (this);
        }
        initialList.activate(Activable.NOTHING, null);
    }
    
    public void pauseApp() {
        ScreensKeeper.playScreen.activate(Activable.PAUSE, null);
    }
    
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
    
    public void activate(byte whatToDo, Object data) {
        if (whatToDo == Activable.END) destroyApp(true);
    }
    
}
