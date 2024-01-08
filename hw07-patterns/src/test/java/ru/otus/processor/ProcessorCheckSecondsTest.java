package ru.otus.processor;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;
import ru.otus.util.CustomDateTimeService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessorCheckSecondsTest {
    Message message;
    CustomDateTimeService service;
    ProcessorCheckSeconds processor;

    @BeforeEach
    void init() {
        service = mock(CustomDateTimeService.class);
        processor = new ProcessorCheckSeconds(service);
        message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field10("field10")
                .field12("field12")
                .build();
    }

    @Test
    void checkEvenSecond() {
        when(service.isEven()).thenReturn(true);

        assertThrows(EvenSecondException.class, () -> processor.process(message));
    }

    @Test
    void checkNotEvenSecond() {
        when(service.isEven()).thenReturn(false);

        AssertionsForClassTypes.assertThat(processor.process(message)).isEqualTo(message);
    }
}
