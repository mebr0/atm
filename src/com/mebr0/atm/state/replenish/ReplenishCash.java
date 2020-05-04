package com.mebr0.atm.state.replenish;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.util.Printer.out;
import static com.mebr0.util.Scanner.ERROR;
import static com.mebr0.util.Scanner.index;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Enter sum to replenish to {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class ReplenishCash extends State {

    private static State state;

    private ReplenishCash() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new ReplenishCash();
        }

        return state;
    }

    @Override
    public State next() {
        int sum = index("Enter cash to replenish");

        if (sum != ERROR) {
            int sumToReplenish = DB.replenishSum(Machine.bin, sum);

            out("Wait for replenishing " + sumToReplenish + "... ");
            return ReplenishTransaction.state();
        }
        else {
            return state;
        }
    }
}
