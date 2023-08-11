import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    Horse horse;

    @Test
    @DisplayName("1-ый параметр null (исключение")
    void constructor1() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 20.3, 305.5));
    }

    @Test
    @DisplayName("1-ый параметр null (сообщение)")
    void constructor2() {
        IllegalArgumentException exceptionActual = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 20.3, 305.5));
        Assertions.assertEquals("Name cannot be null.", exceptionActual.getMessage());
    }
    @DisplayName("1-ый параметр пустая строка (исключение)")
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "   ",
    })
    void constructor3(String str) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(str,20.3,305.5));
    }
    @DisplayName("1-ый параметр пустая строка (сообщение)")
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "   ",
    })
    void constructor4(String str) {
        IllegalArgumentException exceptionActual = assertThrows(IllegalArgumentException.class,
                () -> new Horse(str,20.3,305.5));
        Assertions.assertEquals("Name cannot be blank.", exceptionActual.getMessage());
    }
    @Test
    @DisplayName("2-ой параметр отрицательное число (исключение)")
    void constructor5() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Anfisa", -20.3, 305.5));
    }
    @Test
    @DisplayName("2-ой параметр отрицательное число (сообщение)")
    void constructor6() {
        IllegalArgumentException exceptionActual = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Anfisa", -20.3, 305.5));
        assertEquals("Speed cannot be negative.", exceptionActual.getMessage());
    }
    @Test
    @DisplayName("3-ий параметр отрицательное число (исключение)")
    void constructor7() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Anfisa", 20.3, -305.5));
    }
    @Test
    @DisplayName("3-ий параметр отрицательное число (сообщение)")
    void constructor8() {
        IllegalArgumentException exceptionActual = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Anfisa", 20.3, -305.5));
        assertEquals("Distance cannot be negative.", exceptionActual.getMessage());
    }

    @Test
    void getName() {
        horse = new Horse("Anfisa", 20.3, 305.5);
        String actual = horse.getName();
        String expected = "Anfisa";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void getSpeed() {
        horse = new Horse("Anfisa", 20.3, 305.5);
        double actual = horse.getSpeed();
        double expected = 20.3;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("getDistance() с 3 параметрами")
    void getDistance1() {
        horse = new Horse("Anfisa", 20.3, 305.5);
        double actual = horse.getDistance();
        double expected = 305.5;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("getDistance() с 2 параметрами")
    void getDistance2() {
        horse = new Horse("Anfisa", 20.3);
        double actual = horse.getDistance();
        double expected = 0;
        Assertions.assertEquals(actual, expected);
    }


    @Test
    @DisplayName("move() вызывает getRandomDouble(0.2, 0.9)")
    public void move1() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            Horse horse = new Horse("Anfisa", 20.3, 305.5);

            horse.move();

            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @DisplayName("move() присваивает дистанции значение по формуле")
    @ParameterizedTest
    @CsvSource ({
            "0.3,311.5",
            "0.5,315.5",
            "0.75,320.5",
            "0.762,320.74",
    })
    public void move2(double arg1, double arg2) {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            horse = new Horse("Anfisa", 20, 305.5);
            mocked.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(arg1);

            horse.move();

            assertEquals(horse.getDistance(),arg2);
        }
    }
}