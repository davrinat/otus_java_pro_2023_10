package ru.otus.listener.homework;

import ru.otus.exception.DataException;
import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> cache = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        cache.put(msg.getId(), msg);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        try {
            return Optional.ofNullable(cache.get(id));
        } catch (Exception e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
