package com.mebr0.atm.state.ultra;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.Menu;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Scanner.ask;

/**
 * Initial state of {@link com.mebr0.atm.Machine}
 * Validating bin (Card) and pin code of {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Start extends State {

    private static State state;

    private Start() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new Start();
        }

        return state;
    }

    @Override
    public State next() {
        String bin = ask("Enter bin of card");
        String pin = ask("Enter pin of card");

        if (DB.login(bin, pin)) {
            Machine.bin = bin;
            return Menu.state();
        }
        else {
            error("Wrong bin or pin of card");
            return Halt.state();
        }
    }
}
