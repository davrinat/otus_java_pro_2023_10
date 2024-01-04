package ru.otus.cashmachines;

import ru.otus.utils.Sections;

import java.util.Map;

public interface CashMachine {
    Map<Sections, Integer> getCashStorage();
    long getBalance();
}
