package com.mebr0.atm.state;

/**
 * Final state of {@link com.mebr0.atm.Machine}
 * Error means exit with some errors
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Error extends State {

    public Error() {
        super(true);
    }

    @Override
    public State next() {
        return null;
    }
}
