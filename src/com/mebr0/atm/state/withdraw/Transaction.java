package com.mebr0.atm.state.withdraw;

import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.Error;
import com.mebr0.atm.state.ultra.State;

import java.util.Random;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Printer.print;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Withdraw transaction for {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Transaction extends State {

    private static State state;

    private Transaction() {
        super(false);
    }

    static State state() {
        if (state == null) {
            state = new Transaction();
        }

        return state;
    }

    @Override
    public State next() {
        int delay = new Random().nextInt(5) + 3;

        try {
            Thread.sleep(delay * 1000);

            print("Cash issued");
            return Menu.state();
        }
        catch (InterruptedException e) {
            LOG.error("Thread interrupted during delay");
            error("Interrupted");
            return Error.state();
        }
    }
}
