import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorseTest {

    @Test
    void nullNameTestException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));

    }

    @Test
    void nullNameTestMessage() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void emptyNameTestException(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void emptyNameTestMessage(String name) {
        try {
            new Horse(name, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    void negativeSpeedTestException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("A", - 1, 1));

    }

    @Test
    void negativeSpeedTestMessage() {
        try {
            new Horse("A", - 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void negativeDistanceTestException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("A", 1, - 1));

    }

    @Test
    void negativeDistanceTestMessage() {
        try {
            new Horse("A", 1, - 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getNameTest() {
        Horse horse = new Horse("A", 1, 1);
        assertEquals("A", horse.getName());
    }

    @Test
    void getSpeedTest() {
        Horse horse = new Horse("A", 1, 2);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Horse horse = new Horse("A", 2, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    void getDistanceNullTest() {
        Horse horse = new Horse("A", 2);
        assertEquals(0, horse.getDistance());
    }

    @Test

    void moveTest() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("A", 1, 1);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5})
    void getRandomDoubleTest(double arg) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse horse = new Horse("A", 5, 25);
            double comparisonDistance = horse.getDistance() + horse.getSpeed()*Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(comparisonDistance, horse.getDistance());
        }
    }

}
