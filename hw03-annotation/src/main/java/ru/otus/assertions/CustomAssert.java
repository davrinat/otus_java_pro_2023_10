package ru.otus.assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CustomAssert {
    static Logger log = LoggerFactory.getLogger(CustomAssert.class);

    public void init() {
        log.info("object was init");
    }

    public void customAssertThis(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            throw new RuntimeException("assertion false: expected = " + expected + ", actual = " + actual);
        }

        log.info("assertion true");
    }

    public void destroy() {
        log.info("object was destroy");
    }
}
