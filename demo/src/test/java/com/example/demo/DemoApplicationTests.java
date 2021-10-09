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
        assert Main.firstPlayer.size() == 6;
        assert Main.secondPlayer.size() == 6;
    }
}
