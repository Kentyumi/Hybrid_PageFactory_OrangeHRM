package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Random;

public abstract class AbstractTest {

    protected final Log log;

    protected AbstractTest() {
        log = LogFactory.getLog(getClass());
    }

    protected int randomNumber() {
        Random rand = new Random();
        return rand.nextInt();
    }
}
