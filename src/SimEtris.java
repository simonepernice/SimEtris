/**
*   SimEtris is a Tetris clone
*   Copyright (C) 2006 Simone Pernice <pernice@libero.it>
*
*   This file is part of SimEtris.
*
*   SimEtris is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   SimEtris is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with SimEtris.  If not, see <https://www.gnu.org/licenses/>.
*/

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
