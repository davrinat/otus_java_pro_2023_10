package ru.otus;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"java:S106", "java:S1220", "java:S4738"})
public class HelloOtus {
    public static void main(String[] args) {
        List<Object> list = ImmutableList.of("Hello", "Otus");
        System.out.println(
                FluentIterable.from(list).stream().map(Object::toString).collect(Collectors.joining(", ")));
    }
}
