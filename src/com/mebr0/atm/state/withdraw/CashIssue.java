package com.mebr0.atm.state.withdraw;

import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.Error;
import com.mebr0.atm.state.ultra.State;

import java.util.Random;

import static com.mebr0.util.Printer.*;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Issue cash from {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class CashIssue extends State {

    public CashIssue() {
        super(false);
    }

    @Override
    public State next() {
        int delay = new Random().nextInt(5) + 3;
        out("Wait for withdrawing cash... ");

        try {
            Thread.sleep(delay * 1000);

            print("Cash issued");
            return new Menu();
        }
        catch (InterruptedException e) {
            LOG.error("Thread interrupted during delay");
            error("Interrupted");
            return new Error();
        }
    }
}
