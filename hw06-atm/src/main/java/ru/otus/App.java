package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.services.ATM;
import ru.otus.services.ATMImpl;

public class App {
    private final static Logger log = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {

        ATM cash = new ATMImpl();

        log.info(cash.getQuantityBanknotes());

        log.info(cash.getCash(17650));
        log.info(cash.getQuantityBanknotes());

        log.info(cash.putCash(150));
        log.info(cash.getQuantityBanknotes());

        log.info(cash.getCash(2331));
    }
}
