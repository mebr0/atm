package com.mebr0.atm.state;

/**
 * Final state of {@link com.mebr0.atm.Machine}
 * Halt means exit without errors
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Halt extends State {

    public Halt() {
        super(true);
    }

    @Override
    public State next() {
        return null;
    }
}
