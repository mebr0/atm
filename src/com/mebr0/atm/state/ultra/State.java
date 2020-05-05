package com.mebr0.atm.state.ultra;

import com.mebr0.database.Database;
import com.mebr0.util.Logger;

import java.util.UUID;

/**
 * Base class of states in {@link com.mebr0.atm.Machine}
 *
 * @author A.Yergali
 * @version 1.0
 */
public abstract class State {

    private final String id;
    private final boolean isFinal;

    protected final static Database DB;
    protected final static Logger LOG;

    static {
        DB = Database.getDatabase();
        LOG = Logger.getInstance();
    }

    {
        id = UUID.randomUUID().toString();
    }

    public State(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public String getId() {
        return id;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public abstract State next();
}
