package ru.otus.cashmachines;

import ru.otus.utils.Sections;

import java.util.Map;
import java.util.HashMap;

public class CashMachineImpl implements CashMachine {
    @Override
    public Map<Sections, Integer> getCashStorage() {
        return BanknoteSection.getStorage();
    }

    @Override
    public long getBalance() {
        return BanknoteSection.getStorage().entrySet().stream()
                .mapToInt(
                        x -> x.getValue() * Sections.valueOf(String.valueOf(x.getKey()))
                                .getBanknote().getNominal())
                .sum();
    }

    public static class BanknoteSection {
        private static final Map<Sections, Integer> storage;

        static {
            storage = new HashMap<>();
            storage.put(Sections.SECTION50, 1000);
            storage.put(Sections.SECTION100, 1000);
            storage.put(Sections.SECTION500, 1000);
            storage.put(Sections.SECTION1000, 1000);
            storage.put(Sections.SECTION5000, 1000);
        }

        public static Map<Sections, Integer> getStorage() {
            return storage;
        }
    }
}
