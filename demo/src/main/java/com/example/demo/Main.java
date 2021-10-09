package com.example.demo;

import java.util.*;

public class Main {

    static Suit trump;
    static String motion = "первый";
    static List<Card> cards = new LinkedList<>();
    static List<Card> firstPlayer = new LinkedList<>();
    static List<Card> secondPlayer = new LinkedList<>();
    static Map<String, Integer> tableOfPower = new HashMap<>();


    public static void main(String[] args) {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("[1] - Начать игру; [2] - Завершить");
            String start = in.nextLine();
            if (start.equals("1")) {
                createDeck();
            } else if (start.equals("2")) {
                System.out.println("Завершение...");
                return;
            } else System.out.println("Некорректный ввод данных");
        }
    }

    public static void createDeck() {
        Suit[] suits = Suit.values();
        Value[] values = Value.values();
        for (Suit suit : suits) {
            for (Value value : values) {
                Card card = new Card(value, suit);
                cards.add(card);
            }
        }
    }
    
}

