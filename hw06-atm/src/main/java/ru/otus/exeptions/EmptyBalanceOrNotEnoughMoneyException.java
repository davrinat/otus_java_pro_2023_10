package ru.otus.exeptions;

public class EmptyBalanceOrNotEnoughMoneyException extends RuntimeException {
    public EmptyBalanceOrNotEnoughMoneyException(String message) {
        super(message);
    }
}
