import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    List<Horse> horses = new ArrayList<>();

    @Test
    @DisplayName("Передача в конструктор null (исключение)")
    void constructor1() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Передача в конструктор null (сообщение)")
    void constructor2() {
        IllegalArgumentException exceptionActual = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exceptionActual.getMessage());
    }

    @Test
    @DisplayName("Передача в конструктор пустого списка (исключение)")
    void constructor3() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
    }

    @Test
    @DisplayName("Передача в конструктор пустого списка (сообщение)")
    void constructor4() {
        IllegalArgumentException exceptionActual = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exceptionActual.getMessage());
    }

    @Test
    void getHorses() {
        Horse horse;
        for (int i = 1; i <= 30; i++) {
            horse = new Horse("HorseName" + i, 20.3, 305.5);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(hippodrome.getHorses(), horses);
    }

    @ExtendWith(MockitoExtension.class)
    class VerifyTest {
        @Mock
        Horse horse = new Horse("Anfisa", 20.3, 305.5);

        @Test
        void move() {
            horse.move();
            Mockito.verify(horse).move();
        }
    }
    @Test
    public void move() {
        Horse horse;
        for (int i = 1; i <= 50; i++) {
            horse = new Horse("Anfisa" + i, 20.3, 305.5);
            horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int j = 0; j < 50; j++) {
            Mockito.verify(horses.get(j)).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Anfisa", 20.3, 305.5);
        Horse horse2 = new Horse("Anfisa", 20.3, 307.5);
        Horse horse3 = new Horse("Anfisa", 20.3, 300.5);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(hippodrome.getWinner().getDistance(), horse2.getDistance());
    }
}