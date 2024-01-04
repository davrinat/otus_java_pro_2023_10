package ru.otus.utils;

public enum Sections {
    SECTION50(Banknote.FIFTY),
    SECTION100(Banknote.ONE_HUNDRED),
    SECTION500(Banknote.FIVE_HUNDRED),
    SECTION1000(Banknote.ONE_THOUSAND),
    SECTION5000(Banknote.FIVE_THOUSAND);

    final Banknote name;
    Sections(Banknote name) {
        this.name = name;
    }

    public Banknote getBanknote() {
        return this.name;
    }
}
