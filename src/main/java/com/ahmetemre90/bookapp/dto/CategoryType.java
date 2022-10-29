package com.ahmetemre90.bookapp.dto;

public enum CategoryType {
    SCIENCE("Bilim"),
    HISTORY("Tarih"),
    RELIGION("Din"),
    PHILOSOPHY("Felsefe"),
    OTHER("Diğer");

    private final String value;

    CategoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
