package ru.otus.processor;

import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;
import ru.otus.util.CustomDateTimeService;

public class ProcessorCheckSeconds implements Processor {
    CustomDateTimeService service;

    public ProcessorCheckSeconds(CustomDateTimeService service) {
        this.service = service;
    }

    @Override
    public Message process(Message message) {
        if (service.isEven()) {
            throw new EvenSecondException("It`s an even second");
        }
        return message;
    }
}
