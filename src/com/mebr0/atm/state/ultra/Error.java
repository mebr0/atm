package com.mebr0.atm.state.ultra;

/**
 * Final state of {@link com.mebr0.atm.Machine}
 * Error means exit with some errors
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Error extends State {

    private static State state;

    private Error() {
        super(true);
    }

    public static State state() {
        if (state == null) {
            state = new Error();
        }

        return state;
    }

    @Override
    public State next() {
        return null;
    }
}
