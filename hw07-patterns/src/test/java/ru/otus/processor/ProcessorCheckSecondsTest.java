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
   private Message message;
    private CustomDateTimeService event;
    private ProcessorCheckSeconds processor;

    @BeforeEach
    void init() {
        event = mock(CustomDateTimeService.class);
        message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field10("field10")
                .field12("field12")
                .build();
    }

    @Test
    void checkEvenSecond() {
        when(event.isEven()).thenReturn(true);

        processor = new ProcessorCheckSeconds(event.isEven());

        assertThrows(EvenSecondException.class, () -> processor.process(message));
    }

    @Test
    void checkNotEvenSecond() {
        when(event.isNotEven()).thenReturn(false);

        processor = new ProcessorCheckSeconds(event.isNotEven());

        AssertionsForClassTypes.assertThat(processor.process(message)).isEqualTo(message);
    }
}
