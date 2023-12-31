package screens;

import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

public class HighScoresList extends List implements CommandListener, Activable {
    
    private final static int NUMOFSCORES = 10;
    private PlayerScore[] results;

    private PlayerDataForm playerDataForm;
    
    public HighScoresList() {
        super ("High Score", List.IMPLICIT);
        
        results = new PlayerScore[NUMOFSCORES];
        
        playerDataForm = new PlayerDataForm ();
        
        loadScores();
        
        refreshScreen ();
        
        addCommand(new Command ("BACK", Command.BACK, 0));
        
        setCommandListener(this);        
    }
    
    public void refreshScreen () {
        deleteAll();
        append ("Score      Name", null);
        for (int i=0; i < NUMOFSCORES; ++i) {
            if (results[i] == null) continue;
            append(results[i].toString(), null);
        }
    }
    
    public void loadScores () {
        RecordStore rs ;
        int i, id;
        PlayerScore ps;
        try {
            rs = RecordStore.openRecordStore("FOM", true);
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            for (i=0; i<re.numRecords() && i<NUMOFSCORES; ++i) {
                id = re.nextRecordId();
                addToHighScore (loadScore (rs, id));            
            }
            for (; i<NUMOFSCORES; ++i) {
                ps = new PlayerScore ();
                saveScore(ps, rs);
                addToHighScore (ps);
            }
            rs.closeRecordStore();
        } catch (Throwable t) {return;} 
    }
    
    public boolean isGoodScore (int score) {     
        if (score > results[NUMOFSCORES-1].getScore()) return true;
        return false;
    }
    
    private PlayerScore loadScore (RecordStore rs, int id) throws Exception {
        return new PlayerScore (id, new DataInputStream(new ByteArrayInputStream (rs.getRecord(id))));   
    }
    
    private void resaveScore (PlayerScore ps, RecordStore rs) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        ps.save(new DataOutputStream (baos));
        rs.setRecord(ps.getRecordID(), baos.toByteArray(), 0, baos.size());
    }
    
    private void saveScore (PlayerScore ps, RecordStore rs) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        ps.save(new DataOutputStream (baos));
        ps.setRecordID(rs.addRecord(baos.toByteArray(), 0, baos.size()));             
    }
    
    private PlayerScore addToHighScore (PlayerScore ps) {
        PlayerScore tmp, first=ps;
        for (int i = 0; i < NUMOFSCORES; ++i) {
            if (ps.isBiggerThan (results[i])) {
                tmp = results[i];
                results[i] = ps;
                ps = tmp;
                if (ps == null) break;//That can happen only at the initialization
            }
        }        
        return ps;
    }
    
    public void add (PlayerScore ps) {
        if (! isGoodScore (ps.getScore())) return;
        RecordStore rs;
        try {
            rs = RecordStore.openRecordStore("FOM", true);
            ps.setRecordID(addToHighScore(ps).getRecordID());
            resaveScore(ps, rs);
            rs.closeRecordStore();
        } catch (Throwable t) {}
        
        refreshScreen ();
        select (ps);
    }
    
    private void select (PlayerScore ps) {
        for (int i=0; i < results.length; ++i)
            if (results[i] == ps) {
                setSelectedIndex(i+1, true);
                break;
            }        
    }
    
    public void commandAction (Command c, Displayable d) { 
        ScreensKeeper.initialList.activate(Activable.NOTHING, null);
    }

    public void activate(byte whatToDo, Object data) {
        switch (whatToDo) {
            case Activable.ADDPLAYER:
                add ((PlayerScore) data);            
            case Activable.NOTHING:       
                ScreensKeeper.display.setCurrent(this);
                break;
            case Activable.TRYADDPLAYER:
                if (isGoodScore (((Integer) data).intValue())) playerDataForm.activate(whatToDo, data);
                else ScreensKeeper.initialList.activate (Activable.GAMEOVER, null);
                break;
        }
    }    
}
