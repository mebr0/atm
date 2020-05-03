package com.mebr0.atm.state.ultra;

/**
 * Final state of {@link com.mebr0.atm.Machine}
 * Halt means exit without errors
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Halt extends State {

    private static State state;

    private Halt() {
        super(true);
    }

    public static State state() {
        if (state == null) {
            state = new Halt();
        }

        return state;
    }

    @Override
    public State next() {
        return null;
    }
}
