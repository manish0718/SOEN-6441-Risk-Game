package org.ObserverBasedLogging;

import java.util.Observable;

public class LogEntryBuffer extends Observable {
    private String d_msg;

    public void setMsg(String p_msg) {
        this.d_msg = p_msg;
        setChanged();
        notifyObservers(p_msg);
    }
}
