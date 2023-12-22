package ru.otus.services;

import ru.otus.cashmachines.CashMachine;
import ru.otus.cashmachines.CashMachineImpl;
import ru.otus.exeptions.EmptyBalanceOrNotEnoughMoneyException;
import ru.otus.exeptions.IncorrectBanknoteException;
import ru.otus.exeptions.NegativeAmountException;
import ru.otus.utils.Banknote;
import ru.otus.utils.Sections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ATMImpl implements ATM {
    CashMachine machine;

    public ATMImpl() {
        this.machine = new CashMachineImpl();
    }

    @Override
    public String getQuantityBanknotes() {
        StringBuilder result = new StringBuilder()
                .append("Текущее количество банкнот по номиналу:\n");
        machine.getCashStorage().forEach((key, value) -> result
                .append("Банкнот номиналом по ")
                .append(key.getBanknote().getNominal())
                .append(" = ")
                .append(value)
                .append("\n")
        );
        return result.toString();
    }

    @Override
    public String putCash(long amount) {
        checkAmount(amount);

        Map<Banknote, Integer> numBanknotes = getNumOfBanknotes(amount);
        changeBalance(numBanknotes, machine.getCashStorage(), true);

        return "Баланс после пополнения: " + machine.getBalance();
    }

    @Override
    public String getCash(long amount) {
        checkAmount(amount);
        if (machine.getBalance() < 50 || machine.getBalance() < amount)
            throw new EmptyBalanceOrNotEnoughMoneyException("Not enough money in the ATM");

        Map<Banknote, Integer> numBanknotes = getNumOfBanknotes(amount);
        changeBalance(numBanknotes, machine.getCashStorage(), false);

        StringBuilder result = new StringBuilder()
                .append("Количество банкнот согласно номинала:\n");
        numBanknotes.forEach((key, value) -> result
                .append("Банкнот по ")
                .append(key.getNominal())
                .append(" -> ")
                .append(value)
                .append("\n"));
        return result + "Текущий баланс: " + machine.getBalance();
    }

    private void checkAmount(long amount) {
        if (amount <= 0)
            throw new NegativeAmountException("The amount cannot be less than or equal to 0");
        if (amount < 50 || amount % 50 != 0)
            throw new IncorrectBanknoteException("The requested amount cannot be less than or must be a multiple of 50 rubles.");
    }

    private void changeBalance(Map<Banknote, Integer> numBanknotes, Map<Sections, Integer> cash, boolean isIncreaseBalance) {

        numBanknotes.forEach((key, value) ->
                cash.entrySet().forEach((section) -> {
                    if (Objects.equals(key, section.getKey().getBanknote())) {
                        if (isIncreaseBalance) {
                            section.setValue(section.getValue() + value);
                        } else {
                            section.setValue(section.getValue() - value);
                        }
                    }
                }));
    }

    private Map<Banknote, Integer> getNumOfBanknotes(long count) {
        Map<Banknote, Integer> numBanknotes = new HashMap<>();

        if (count >= Banknote.FIVE_THOUSAND.getNominal()) {
            numBanknotes.put(Banknote.FIVE_THOUSAND, (int) (count / Banknote.FIVE_THOUSAND.getNominal()));
            count = count % Banknote.FIVE_THOUSAND.getNominal();
        }
        if (count >= Banknote.ONE_THOUSAND.getNominal()) {
            numBanknotes.put(Banknote.ONE_THOUSAND, (int) (count / Banknote.ONE_THOUSAND.getNominal()));
            count = count % Banknote.ONE_THOUSAND.getNominal();
        }
        if (count >= Banknote.FIVE_HUNDRED.getNominal()) {
            numBanknotes.put(Banknote.FIVE_HUNDRED, (int) (count / Banknote.FIVE_HUNDRED.getNominal()));
            count = count % Banknote.FIVE_HUNDRED.getNominal();
        }
        if (count >= Banknote.ONE_HUNDRED.getNominal()) {
            numBanknotes.put(Banknote.ONE_HUNDRED, (int) (count / Banknote.ONE_HUNDRED.getNominal()));
            count = count % Banknote.ONE_HUNDRED.getNominal();
        }
        if (count >= Banknote.FIFTY.getNominal()) {
            numBanknotes.put(Banknote.FIFTY, (int) (count / Banknote.FIFTY.getNominal()));
        }
        return numBanknotes;
    }
}
