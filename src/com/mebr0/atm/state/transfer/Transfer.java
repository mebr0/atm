package com.mebr0.atm.state.transfer;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.Error;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Printer.out;
import static com.mebr0.util.Scanner.*;

/**
 * Intermediate state of {@link Machine}
 * Enter sum to transfer from and to {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Transfer extends State {

    private static State state;

    private Transfer() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new Transfer();
        }

        return state;
    }

    @Override
    public State next() {
        double sum = number("Enter sum to transfer");

        if (sum == ERROR)
            return state;

        String accountBin = ask("Enter bin of account to transfer to");

        if (!DB.accountExists(accountBin)) {
            error("Account with bin " + accountBin + " not found");
            return state;
        }

        if (DB.checkSum(Machine.bin, sum)) {
            double transferSum = DB.transfer(Machine.bin, accountBin, sum);

            if (transferSum == sum) {
                out("Wait for transferring " + transferSum + " to " + accountBin + "... ");
                return Transaction.state();
            }
            else {
                error("Not enough cash on account or on ATM");
                return Error.state();
            }
        }
        else {
            error("Not enough cash on account");
            return Menu.state();
        }
    }
}
