import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SampleTest {

    @Test
    public void checkTest(){
        Sample sample = new Sample();
        assertTrue(sample.check());
    }
}
