import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    @Test
    public void testFlik(){
        int i = 0;
        for (int j = 0; i <= 128; ++i, ++j) {
            assertTrue(Flik.isSameNumber(i, j));
        }
    }
}
