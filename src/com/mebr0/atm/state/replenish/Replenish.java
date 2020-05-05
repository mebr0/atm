package com.mebr0.atm.state.replenish;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.ultra.Error;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Printer.out;
import static com.mebr0.util.Scanner.ERROR;
import static com.mebr0.util.Scanner.index;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Enter sum to replenish to {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Replenish extends State {

    private static State state;

    private Replenish() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new Replenish();
        }

        return state;
    }

    @Override
    public State next() {
        int sum = index("Enter cash to replenish");

        if (sum != ERROR) {
            int replenishToAccount = (int) DB.replenishSum(Machine.bin, sum);
            Machine.replenishCash(sum);

            if (replenishToAccount == sum) {
                out("Wait for replenishing " + replenishToAccount + "... ");
                return Transaction.state();
            }
            else {
                error("Error occurred while replenishing");
                return Error.state();
            }
        }
        else {
            return state;
        }
    }
}
