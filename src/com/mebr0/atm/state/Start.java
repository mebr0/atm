package com.mebr0.atm.state;

import com.mebr0.atm.Machine;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Scanner.ask;

/**
 * Initial state of {@link com.mebr0.atm.Machine}
 * Validating bin (Card) and pin code of {@link com.mebr0.db.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Start extends State {

    public Start() {
        super(false);
    }

    @Override
    public State next() {
        String bin = ask("Enter bin of card");
        String pin = ask("Enter pin of card");

        if (DB.login(bin, pin)) {
            Machine.bin = bin;
            return new Menu();
        }
        else {
            error("Wrong bin or pin of card");
            return new Halt();
        }
    }
}
