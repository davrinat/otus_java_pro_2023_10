package ru.otus.exeptions;

public class IncorrectBanknoteException extends RuntimeException {
    public IncorrectBanknoteException(String message) {
        super(message);
    }
}
