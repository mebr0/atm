package com.mebr0.atm;

import com.mebr0.atm.state.ultra.Start;
import com.mebr0.atm.state.ultra.State;
import com.mebr0.database.Database;
import com.mebr0.util.Logger;

/**
 * Class for imitating ATM based on FSM {@link State}s
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Machine {

    private State current;
    public static String bin;

    private final Logger LOG = Logger.getInstance();

    {
        current = Start.state();
    }

    public void start() {
        LOG.info("Session started");

        while (!current.isFinal()) {
            current = current.next();
        }

        current.next();
        LOG.info("Session finished");

        Database.getDatabase().save();
        LOG.info("All changes saved");
    }
}
