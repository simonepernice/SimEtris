package screens;

import java.io.*;

public class PlayerScore {
    private String name;
    private int score;
    private int recordID;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
        recordID = -1;
    }
    
    public PlayerScore () {
        this ("--- void ---", 0);
    }
    
    public boolean isBiggerThan (PlayerScore b) {
        if (b == null) return true;
        return (score - b.score) > 0;
    }
    
    public void save (DataOutputStream dos) throws IOException {
        dos.writeUTF(name);
        dos.writeInt(score);
    }
    
    public PlayerScore (int recordID, DataInputStream dis) throws IOException {
        this.recordID = recordID;
        name = dis.readUTF();
        score = dis.readInt();
    }
    
    public int getRecordID () {
        return recordID;
    }
    
    public void setRecordID (int rid) {
        recordID = rid;
    }   
    
    public int getScore () {
        return score;
    }
    
    public String getName () {
        return name;
    }
    
    public String toString () {
        String sscore = Integer.toString(score);
        return "00000".substring(sscore.length()) + sscore + "    " + name;
    }        
}
