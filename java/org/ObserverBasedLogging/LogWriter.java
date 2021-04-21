package org.ObserverBasedLogging;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LogWriter {
    public static Logger LOGGER;
    public LogWriter() {
    try {
        FileHandler l_handler = new FileHandler("default.log", true);
        this.LOGGER = Logger.getLogger("Team 12");
        this.LOGGER.addHandler(l_handler);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    }
    public static void writeLog(String p_msg) throws Exception {
        LOGGER.severe(p_msg);
    }
}
