package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
                createTrump();
                createTableOfPower();
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

    public static void createTrump(){
        trump = Suit.values()[(int) (Math.random()*Suit.values().length)];
        System.out.println("Козырь - " + trump.suit);
    }

    public static boolean checkFight(Card enemyCard, Card card) {

        // Если моя карта козырь, а вражеская - нет
        if(card.getSuit().equals(trump.suit) && !enemyCard.getSuit().equals(trump.suit)){
            return true;
        }

        // Если карты разные по масти
        if(!card.getSuit().equals(enemyCard.getSuit()))
            return false;

        // Сравнение карт по силе
        if(tableOfPower.get(card.getValue()) > tableOfPower.get(enemyCard.getValue()))
            return true;
        else return false;

    }

    @Test
    void testPlayerDefend() {
        Main.trump = Suit.P;
        Main.createTableOfPower();
        InputStream inputStream = System.in;
        List<Card> list = new LinkedList<>();
        list.add(new Card(Value.ACE, Suit.P));
        list.add(new Card(Value.SEVEN, Suit.CH));

        ByteArrayInputStream in = new ByteArrayInputStream("1\n0".getBytes());
        System.setIn(in);
        Main.playerDefend(new Card(Value.QUEEN, Suit.P), list, "второй");
        assert list.size() == 3;

        in = new ByteArrayInputStream("1\n1".getBytes());
        System.setIn(in);
        assert Main.playerDefend(new Card(Value.QUEEN, Suit.P), list, "второй");

        System.setIn(inputStream);

    }

}

