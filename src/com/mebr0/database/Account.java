package com.mebr0.database;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class for bank accounts
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Account implements Serializable {

    private final String bin;
    private final String pin;
    private double sum;

    public Account(String bin, String pin, double sum) {
        this.bin = bin;
        this.pin = pin;
        this.sum = sum;
    }

    public boolean login(String bin, String pin) {
        return this.bin.equals(bin) && this.pin.equals(pin);
    }

    public boolean canBeWithdraw(double sum) {
        return this.sum >= sum;
    }

    public double withdraw(double sum) {
        if (this.sum >= sum) {
            this.sum -= sum;
            return sum;
        }

        return 0;
    }

    public double replenish(double sum) {
        this.sum += sum;
        return sum;
    }

    public String getBin() {
        return bin;
    }

    public String getPin() {
        return pin;
    }

    public double getSum() {
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Account))
            return false;

        Account account = (Account) o;
        return Double.compare(account.sum, sum) == 0 &&
                bin.equals(account.bin) &&
                pin.equals(account.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bin, pin, sum);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [bin: " + bin + ", sum: " + sum + "]";
    }
}
