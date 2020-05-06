package com.mebr0.atm.state.pin;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.Error;
import com.mebr0.atm.state.ultra.Halt;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.database.Database.ERROR_SUM;
import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Printer.print;
import static com.mebr0.util.Scanner.pin;

/**
 * Intermediate state of {@link Machine}
 * Change pin of {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class ChangePin extends State {

    private static State state;

    private ChangePin() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new ChangePin();
        }

        return state;
    }

    @Override
    public State next() {
        String pin = pin("Enter new pin");

        if (!"".equals(pin)) {
            if (DB.changePin(Machine.bin, pin)) {
                print("Pin changed");
                return Halt.state();
            }
            else {
                error("Error with account");
                return Error.state();
            }
        }
        else {
            print("Incorrect pin (4-digits)");
            return state;
        }
    }
}
