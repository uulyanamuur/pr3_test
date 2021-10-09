package com.example.demo;

public class Card {

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    private Value value;
    private Suit suit;

    public String getValue() {
        return value.value;
    }

    public void setValue(String value) {
        this.value = Value.valueOf(value);
    }

    public String getSuit() {
        return suit.suit;
    }

    public void setSuit(String suit) {
        this.suit = Suit.valueOf(suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", suit=" + suit +
                '}';
    }
}
