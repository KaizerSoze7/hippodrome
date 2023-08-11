import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    void mainTest() {
        String[] args = {};
        try {
            Main.main(args);
        }
        catch (Exception e) {
            System.out.println("Исключение");
        }
    }
}