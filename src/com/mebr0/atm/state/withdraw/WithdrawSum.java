package com.mebr0.atm.state.withdraw;

import com.mebr0.atm.Machine;
import com.mebr0.atm.state.Menu;
import com.mebr0.atm.state.ultra.State;

import static com.mebr0.util.Printer.error;
import static com.mebr0.util.Printer.out;
import static com.mebr0.util.Scanner.index;

/**
 * Intermediate state of {@link com.mebr0.atm.Machine}
 * Enter sum to withdraw from {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 2.0
 */
public class WithdrawSum extends State {

    private static State state;

    private WithdrawSum() {
        super(false);
    }

    public static State state() {
        if (state == null) {
            state = new WithdrawSum();
        }

        return state;
    }

    @Override
    public State next() {
        int sum = index("Enter sum to withdraw");

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
