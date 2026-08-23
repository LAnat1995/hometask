package com.github.LAnat.concurrentbank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private final List<BankAccount> accounts = new ArrayList<>();
    private final AtomicLong accountCounter = new AtomicLong(1);

    public BankAccount createAccount(long initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        String accountNumber = String.format("ACC%04d", accountCounter.getAndIncrement());
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        synchronized (accounts) {
            accounts.add(account);
        }
        return account;
    }

    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, long amount) {
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Accounts cannot be null");
        }
        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        ReentrantLock firstLock;
        ReentrantLock secondLock;

        if (fromAccount.getAccountNumber().compareTo(toAccount.getAccountNumber()) < 0) {
            firstLock = fromAccount.getLock();
            secondLock = toAccount.getLock();
        } else {
            firstLock = toAccount.getLock();
            secondLock = fromAccount.getLock();
        }

        firstLock.lock();
        secondLock.lock();

        try {
            if (fromAccount.getBalance() < amount) {
                return false;
            }

            boolean withdrawn = fromAccount.withdraw(amount);
            if (!withdrawn) {
                return false;
            }
            toAccount.deposit(amount);

            return true;
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }

    public long getTotalBalance() {
        synchronized (accounts) {
            return accounts.stream()
                    .mapToLong(BankAccount::getBalance)
                    .sum();
        }
    }
}
