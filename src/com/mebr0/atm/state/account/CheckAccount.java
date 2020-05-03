package com.mebr0.atm.state.account;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.Error;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Printer.print;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Check cash of {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class CheckAccount extends State {

    private static State state;

    private CheckAccount() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new CheckAccount();
        }

        return state;
    }

    @Override
    public State next() {
        double sum = DB.getSum(Machine.bin);

        if (sum != -1) {
            print("Current balance: " + sum);
            return Menu.state();
        }
        else {
            error("Error with account");
            return Error.state();
        }
    }
}
