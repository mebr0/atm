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

    private final Logger LOG = Logger.getInstance();
    private final Database DB = Database.getDatabase();

    private State current;
    public static String bin;
    public static int cash;

    {
        current = Start.state();
    }

    public void start() {
        cash = DB.getSum();

        LOG.info("Session started");

        while (!current.isFinal()) {
            current = current.next();
        }

        current.next();
        LOG.info("Session finished");

        DB.setSum(cash);
        DB.save();
        LOG.info("All changes saved");

        LOG.info(String.valueOf(cash));
    }

    public static boolean isEnoughCash(int sum) {
        return cash >= sum;
    }

    public static int withdrawCash(int sum) {
        if (cash >= sum) {
            cash -= sum;
            return sum;
        }

        return 0;
    }

    public static void replenishCash(int sum) {
        cash += sum;
    }
}
