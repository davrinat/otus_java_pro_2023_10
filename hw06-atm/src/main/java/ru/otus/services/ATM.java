package ru.otus.services;

public interface ATM {
    String getCash(long amount);
    String putCash(long amount);
    String getQuantityBanknotes();
}
