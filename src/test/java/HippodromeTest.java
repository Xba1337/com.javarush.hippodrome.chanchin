import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    void nullListTestException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void nullListTestMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    void emptyListTestException(){
        List<Horse> emptyList = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
    }

    @Test
    void emptyListTestMessage(){
        List<Horse> emptyList = new ArrayList<>();
        try {
            new Hippodrome(emptyList);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void getHorseTest(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Horse#" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horseList);

        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    void moveTest(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horseList.add(horse);
        }
        new Hippodrome(horseList).move();
        for (Horse horse:
             horseList) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerTest(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(new Horse("Horse#" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        Horse winner = horseList.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(winner, hippodrome.getWinner());
    }
}
