package com.mebr0.atm.state.ultra;

import com.mebr0.database.Database;
import com.mebr0.util.Logger;

/**
 * Base class of states in {@link com.mebr0.atm.Machine}
 *
 * @author A.Yergali
 * @version 2.0
 */
public abstract class State {

    private final boolean isFinal;

    protected final static Database DB;
    protected final static Logger LOG;

    static {
        DB = Database.getDatabase();
        LOG = Logger.getInstance();
    }

    public State(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public abstract State next();
}
