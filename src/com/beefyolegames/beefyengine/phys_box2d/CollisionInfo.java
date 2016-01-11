package com.beefyolegames.beefyengine.phys_box2d;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 26/04/14.
 */
public class CollisionInfo {
    private int myID;
    private List<Integer> otherIDs;

    public CollisionInfo(int myID){
        this.myID = myID;
        otherIDs = new ArrayList<Integer>();
    }

    public int getMyId(){
        return myID;
    }
    public synchronized void addOtherIDToList(int otherId){
        otherIDs.add(otherId);
    }
    public void removeIDFromList(int otherId){
        Integer IDToRemove = -1;
        synchronized (this){
            for (Integer ID : otherIDs){
                if(ID == otherId){
                    IDToRemove = ID;
                }
            }
        }
        if(IDToRemove != -1){
            otherIDs.remove(IDToRemove);
        }
    }
    public synchronized boolean amICollidingWith(int otherId){
        for (Integer ID : otherIDs){
            if(ID == otherId){
                return true;
            }
        }
        return false;
    }
}
