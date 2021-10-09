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

    public static boolean playerDefend(Card enemyCard, List<Card> current, String nextName){
        Scanner in = new Scanner(System.in);
        System.out.println("Ходит "+nextName+" игрок (Нажмите enter)");
        String s = in.nextLine();
        while (true) {
            System.out.println("Против вас выставили карту:");
            System.out.printf("%s %s\n", enemyCard.getValue(), enemyCard.getSuit());
            System.out.println("Выберете действие:");
            for (Card card : current) {
                System.out.printf("[%s] - %s %s\n", current.indexOf(card) + 1, card.getValue(), card.getSuit());
            }
            System.out.print("[0] - Взять карту\n");
            String step = in.nextLine();
            try{
                int intStep = Integer.parseInt(step);
                if(intStep == 0){
                    current.add(enemyCard);
                    return false;
                }
                else {
                    Card card = current.get(intStep - 1);
                    if(checkFight(enemyCard, card)){
                        current.remove(card);
                        System.out.println("Бито!");
                        return true;
                    }else
                        System.out.println("Данной картой нельзя побить вражескую. Попробуйте ещё раз");
                }
            }catch (Exception ignored){
                System.out.println("Некорректные данные, попробуйте ещё раз");
            }

        }
    }

}

