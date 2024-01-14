package ru.otus.processor;

import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;

public class ProcessorCheckSeconds implements Processor {
    private final boolean event;

    public ProcessorCheckSeconds(boolean event) {
        this.event = event;
    }

    @Override
    public Message process(Message message) {
        if (event) {
            throw new EvenSecondException("It`s an even second");
        }
        return message;
    }
}
