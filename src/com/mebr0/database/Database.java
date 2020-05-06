package com.mebr0.database;

import com.mebr0.util.Serializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing and manipulating data
 * Uses java serialization and {@link Serializer}
 *
 * Have {@link #save()} and {@link #load()} methods
 *
 * @author A.Yergali
 * @version 2.2
 */
public class Database {

    private static Database database = null;

    public static final int ERROR_SUM = -1;

    private Sum sum;

    private Database() {

    }

    public static Database getDatabase() {
        if (database == null)
            database = new Database();

        return database;
    }

    private List<Account> accountList;

    {
        load();
        checkList();
    }

    public boolean login(String bin, String pin) {
        return accountList.stream().
                anyMatch(account -> account.login(bin, pin));
    }

    public boolean checkSum(String bin, double sum) {
        Account account = accountList.stream().
                filter(acc -> acc.getBin().equals(bin)).
                findFirst().
                orElse(null);

        if (account == null)
            return false;

        return account.canBeWithdraw(sum);
    }

    public double withdrawSum(String bin, double sum) {
        Account account = accountList.stream().
                filter(acc -> acc.getBin().equals(bin)).
                findFirst().
                orElse(null);

        if (account == null || !account.canBeWithdraw(sum))
            return ERROR_SUM;

        return account.withdraw(sum);
    }

    public double replenishSum(String bin, int sum) {
        Account account = accountList.stream().
                filter(acc -> acc.getBin().equals(bin)).
                findFirst().
                orElse(null);

        if (account == null)
            return ERROR_SUM;

        return account.replenish(sum);
    }

    public double getSum(String bin) {
        Account account = accountList.stream().
                filter(acc -> acc.getBin().equals(bin)).
                findFirst().
                orElse(null);

        if (account == null)
            return ERROR_SUM;

        return account.getSum();
    }

    public boolean accountExists(String bin) {
        return accountList.stream().
                anyMatch(account -> account.getBin().equals(bin));
    }

    public double transfer(String bin, String otherBin, double sum) {
        Account account = accountList.stream().
                filter(acc -> acc.getBin().equals(bin)).
                findFirst().
                orElse(null);

        Account otherAccount = accountList.stream().
                filter(acc -> acc.getBin().equals(otherBin)).
                findFirst().
                orElse(null);

        if (account == null || otherAccount == null || !account.canBeWithdraw(sum))
            return ERROR_SUM;

        account.withdraw(sum);
        otherAccount.replenish(sum);

        return sum;
    }

    public int getSum() {
        return sum.value;
    }

    public void setSum(int sum) {
        this.sum.value = sum;
    }

    public boolean changePin(String bin, String newPin) {
        Account account = accountList.stream().
                filter(acc -> acc.getBin().equals(bin)).
                findFirst().
                orElse(null);

        if (account == null)
            return false;

        account.setPin(newPin);

        return true;
    }

    private void checkList() {
        if (accountList == null) {
            accountList = new ArrayList<>();

            accountList.add(new Account("121212121212", "1234", 32222));
            accountList.add(new Account("123123123123", "1234", 32222));
            accountList.add(new Account("123412341234", "1234", 32222));
        }
    }

    private void load() {
        accountList = Serializer.deserializeList("accounts.out", Account.class);
        sum = Serializer.deserialize("sum.out", Sum.class);

        checkList();

        if (sum == null || Sum.MIN_LIMIT > sum.value) {
            sum = new Sum();
        }
    }

    public boolean save() {
        return Serializer.serialize("accounts.out", accountList) && Serializer.serialize("sum.out", sum);
    }

    /**
     * Class for serializing cash of {@link com.mebr0.atm.Machine}
     *
     * @version 1.0
     */
    static class Sum implements Serializable {

        public int value;

        public static int MIN_LIMIT = 50000;    // Minimum limit for replenish cash of ATM again

        {
            value = 300000;
        }
    }
}
