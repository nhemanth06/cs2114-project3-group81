import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class CafeControllerTest
{

    @Test
    void parseChoiceAcceptsNumberInRange() {
        assertEquals(3, CafeController.parseChoice("3", 1, 6));
    }


    @Test
    void parseChoiceAcceptsBothEndsOfTheRange() {
        assertEquals(1, CafeController.parseChoice("1", 1, 6));
        assertEquals(6, CafeController.parseChoice("6", 1, 6));
        assertEquals(0, CafeController.parseChoice("0", 0, 5));
    }


    @Test
    void parseChoiceIgnoresSurroundingSpaces() {
        assertEquals(4, CafeController.parseChoice("  4  ", 0, 5));
    }


    @Test
    void parseChoiceRejectsNonNumbers() {
        assertThrows(IllegalArgumentException.class,
            () -> CafeController.parseChoice("abc", 1, 6));
        assertThrows(IllegalArgumentException.class,
            () -> CafeController.parseChoice("", 1, 6));
        assertThrows(IllegalArgumentException.class,
            () -> CafeController.parseChoice("2.5", 1, 6));
        assertThrows(IllegalArgumentException.class,
            () -> CafeController.parseChoice(null, 1, 6));
    }


    @Test
    void parseChoiceRejectsOutOfRangeNumbers() {
        assertThrows(IllegalArgumentException.class,
            () -> CafeController.parseChoice("0", 1, 6));
        assertThrows(IllegalArgumentException.class,
            () -> CafeController.parseChoice("7", 1, 6));
        assertThrows(IllegalArgumentException.class,
            () -> CafeController.parseChoice("-1", 0, 5));
    }
}
