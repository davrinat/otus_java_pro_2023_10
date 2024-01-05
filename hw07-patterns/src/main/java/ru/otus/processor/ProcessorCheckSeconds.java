package ru.otus.processor;

import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;

public class ProcessorCheckSeconds implements Processor {
    int second;

    public ProcessorCheckSeconds(int second) {
        this.second = second;
    }

    @Override
    public Message process(Message message) {
        if (second % 2 == 0) {
            throw new EvenSecondException("It`s an even second");
        }
        return message;
    }
}
