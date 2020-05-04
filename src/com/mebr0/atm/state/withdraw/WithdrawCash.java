package com.mebr0.atm.state.withdraw;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Printer.out;
import static com.mebr0.util.Scanner.ERROR;
import static com.mebr0.util.Scanner.index;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Enter sum to withdraw from {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class WithdrawCash extends State {

    private static State state;

    private WithdrawCash() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new WithdrawCash();
        }

        return state;
    }

    @Override
    public State next() {
        int sum = index("Enter sum to withdraw");

        if (sum == ERROR) {
            return state;
        }

        if (DB.checkSum(Machine.bin, sum)) {
            double sumToWithdraw = DB.withdrawSum(Machine.bin, sum);

            out("Wait for withdrawing " + sumToWithdraw + "... ");
            return CashIssue.state();
        }
        else {
            error("Not enough cash on account");
            return Menu.state();
        }
    }
}
