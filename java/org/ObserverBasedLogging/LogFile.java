package org.ObserverBasedLogging;

import java.util.Observable;
import java.util.Observer;

public class LogFile implements Observer {
    private String d_msg;

    public String getD_msg() {
        return d_msg;
    }

    public void setMsg(String p_msg) throws Exception {
        this.d_msg = p_msg;
        LogWriter.writeLog(p_msg);
    }

    @Override
    public void update(Observable o, Object msg) {
        try {
            this.setMsg((String) msg);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
