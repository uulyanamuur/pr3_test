package com.example.demo;

public enum Value {

    SIX("Шесть"),SEVEN("Семь"),EIGHT("Восемь"),NINE("Девять"),TEN("Десять"),
    JACK("Валет"), QUEEN("Дама"), KING("Король"), ACE("Туз");

    String value;

    Value(String value) {
        this.value = value;
    }
}
