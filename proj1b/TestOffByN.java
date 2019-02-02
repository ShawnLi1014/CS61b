import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator ob2 = new OffByN(2);

    @Test
    public void testOffByN() {
        assertTrue(ob2.equalChars('a', 'c'));
    }
}
