package com.mebr0.atm.state;

import com.mebr0.atm.state.account.CheckAccount;
import com.mebr0.atm.state.pin.ChangePin;
import com.mebr0.atm.state.replenish.Replenish;
import com.mebr0.atm.state.transfer.Transfer;
import com.mebr0.atm.state.ultra.Halt;
import com.mebr0.atm.state.ultra.State;
import com.mebr0.atm.state.withdraw.Withdraw;

import static com.mebr0.util.Printer.*;
import static com.mebr0.util.Scanner.index;

/**
 * Main state of {@link com.mebr0.atm.Machine}
 * Menu contains all options of ATM
 *
 * @author A.Yergali
 * @version 3.0
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
        String[] options = { "Withdraw", "Check cash", "Replenish", "Transfer", "Change pin", "Exit" };
        State[] states = { Withdraw.state(), CheckAccount.state(), Replenish.state(), Transfer.state(),
                ChangePin.state(), Halt.state() };

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
