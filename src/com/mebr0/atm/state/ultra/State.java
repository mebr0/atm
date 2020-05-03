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

    protected final Database DB;
    protected final Logger LOG;

    {
        id = UUID.randomUUID().toString();
        DB = Database.getDatabase();
        LOG = Logger.getInstance();
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
