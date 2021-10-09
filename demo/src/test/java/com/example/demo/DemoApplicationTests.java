package com.example.demo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {


    @Test
    void testCreateDeck() {
        //Main.cleanAllDecks();
        assert Main.cards.isEmpty();
        Main.createDeck();
        assert Main.cards.size() == 36;
    }

    @Test
    void testCreateArms() {
        Main.createDeck();
        Main.firstPlayer = new LinkedList<>();
        Main.secondPlayer = new LinkedList<>();
        Main.createArms();
        assert Main.firstPlayer.size() == 6;
        assert Main.secondPlayer.size() == 6;
    }

    @Test
    void testCleanAllDecks() {
        Main.createDeck();
        Main.createArms();
        Main.cleanAllDecks();
        assert Main.cards.isEmpty();
        assert Main.firstPlayer.isEmpty();
        assert Main.secondPlayer.isEmpty();
    }

    @Test
    void testCreateTableOfPower() {
        Main.tableOfPower = new HashMap<>();
        Main.createTableOfPower();
        assert Main.tableOfPower.size() ==9;
    }

    @Test
    void testCreateTrump() {
        Main.trump = null;
        Main.createTrump();
        assert Main.trump != null;
    }

    @Test
    void testCheckFight() {
        Main.trump = Suit.B;
        Main.createTableOfPower();
        Card enemy = new Card(Value.ACE, Suit.B);
        Card my = new Card(Value.JACK, Suit.B);
        assert !Main.checkFight(enemy, my);

        enemy = new Card(Value.SEVEN, Suit.B);
        my = new Card(Value.JACK, Suit.B);
        assert Main.checkFight(enemy, my);

        enemy = new Card(Value.ACE, Suit.CH);
        my = new Card(Value.JACK, Suit.B);
        assert Main.checkFight(enemy, my);

        enemy = new Card(Value.EIGHT, Suit.CH);
        my = new Card(Value.NINE, Suit.CH);
        assert Main.checkFight(enemy, my);

        enemy = new Card(Value.KING, Suit.P);
        my = new Card(Value.QUEEN, Suit.P);
        assert !Main.checkFight(enemy, my);

        enemy = new Card(Value.SEVEN, Suit.CH);
        my = new Card(Value.JACK, Suit.P);
        assert !Main.checkFight(enemy, my);
    }


}
