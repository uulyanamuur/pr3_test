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
                createTableOfPower();s
                cleanAllDecks();
                createDeck();
                createArms();
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

    public static void createArms(){
        while (firstPlayer.size()<6){
            Card card = cards.get((int) (Math.random()*cards.size()));
            firstPlayer.add(card);
            cards.remove(card);
            card = cards.get((int) (Math.random()*cards.size()));
            secondPlayer.add(card);
            cards.remove(card);
        }
//        System.out.println(firstPlayer);
//        System.out.println(secondPlayer);
    }

    public static void cleanAllDecks(){
        cards.clear();
        firstPlayer.clear();
        secondPlayer.clear();
    }

    public static void createTableOfPower(){
        List<Value> values = Arrays.asList(Value.values());
        for(Value value: values){
            tableOfPower.put(value.value, values.indexOf(value));
        }
    }

}

