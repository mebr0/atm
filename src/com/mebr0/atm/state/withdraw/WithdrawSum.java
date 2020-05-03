package com.mebr0.atm.state.withdraw;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Scanner.index;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Enter sum to withdraw from {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class WithdrawSum extends State {

    public WithdrawSum() {
        super(false);
    }

    @Override
    public State next() {
        int sum = index("Enter sum to withdraw");

        if (DB.checkSum(Machine.bin, sum)) {
            return new CashIssue();
        }
        else {
            error("Not enough cash on account");
            return new Menu();
        }
    }
}
