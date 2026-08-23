package com.github.LAnat.concurrentbank;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
public class BankAccount {
    private final String accountNumber;
    private long balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(String accountNumber, long initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        lock.lock();
        try {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        } finally {
            lock.unlock();
        }
    }
}
