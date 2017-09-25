import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class minimumWindSpeed {

        @Test
        public void testMinimumWindSpeed() {
            assertFalse(getWindSpeed() < 0);
        }
    }
