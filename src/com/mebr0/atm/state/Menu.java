package com.mebr0.atm.state;

import com.mebr0.atm.state.account.CheckAccount;
import com.mebr0.atm.state.replenish.ReplenishCash;
import com.mebr0.atm.state.ultra.Halt;
import com.mebr0.atm.state.ultra.State;
import com.mebr0.atm.state.withdraw.WithdrawCash;

import static com.mebr0.util.Printer.*;
import static com.mebr0.util.Scanner.index;

/**
 * Main state of {@link com.mebr0.atm.Machine}
 * Menu contains all options of ATM
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Menu extends State {

    private static State state;

    private Menu() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new Menu();
        }

        return state;
    }

    @Override
    public State next() {
        String[] options = { "Withdraw", "Check cash", "Replenish", "Exit" };
        State[] states = { WithdrawCash.state(), CheckAccount.state(), ReplenishCash.state(), Halt.state() };

        print("Choose option");
        options(options);

        int option = index();

        if (option < 1 || option > options.length) {
            error("Invalid option");
            return Menu.state();
        }

        return states[option - 1];
    }
}
