package com.mebr0.atm.state;

import com.mebr0.atm.state.ultra.Error;
import com.mebr0.atm.state.ultra.Halt;
import com.mebr0.atm.state.ultra.State;
import com.mebr0.atm.state.withdraw.WithdrawSum;

import static com.mebr0.util.Printer.*;
import static com.mebr0.util.Scanner.index;

/**
 * Main state of {@link com.mebr0.atm.Machine}
 * Menu contains all options of ATM
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Menu extends State {

    public Menu() {
        super(false);
    }

    @Override
    public State next() {
        String[] options = { "Withdraw", "Exit" };
        Class<?>[] classes = { WithdrawSum.class, Halt.class };

        print("Choose option");
        options(options);

        int option = index();

        if (option < 1 || option > options.length) {
            error("Invalid option");
            return new Menu();
        }

        try {
            return (State) classes[option - 1].newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            LOG.error("Cannot call empty constructor of " + classes[option - 1].getSimpleName());
            return new Error();
        }
    }
}
