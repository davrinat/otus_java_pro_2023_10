package ru.otus.processor;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProcessorCheckSecondsTest {
    Message message;

    @BeforeEach
    void init() {
        message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field10("field10")
                .field12("field12")
                .build();
    }

    @Test
    void checkEvenSecond() {
        var processor = new ProcessorCheckSeconds(22);

        assertThrows(EvenSecondException.class, () -> processor.process(message));
    }

    @Test
    void checkNotEvenSecond() {
        var processor = new ProcessorCheckSeconds(41);

        AssertionsForClassTypes.assertThat(processor.process(message)).isEqualTo(message);
    }
}
