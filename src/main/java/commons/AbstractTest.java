package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;

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

    public boolean checkTrue(boolean condition) {
        boolean isCondition = true;
        try {
            if (condition == true) {
                log.info("---------PASSED-----------");
            } else
                log.info("---------FAILED-----------");

        } catch (Throwable e) {
            isCondition = false;
        }
        return isCondition;
    }

    public boolean verifyTrue(boolean condition) {
        return checkTrue(condition);
    }

    public boolean checkFailed(boolean condition) {
        boolean isFailed = true;
        try {
            if (condition == false) {
                log.info("---------PASSED-----------");
            } else
                log.info("---------FAILED-----------");
        } catch (Throwable e) {
            isFailed = false;
        }
        return isFailed;
    }

    public boolean verifyFailed(boolean condition) {
        return checkFailed(condition);
    }

    public boolean checkEquals(Object actual, Object expected) {
        boolean isEqual = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("---------PASSED-----------");
        } catch (Throwable e) {
            isEqual = false;
            log.info("---------FAILED-----------");
        }
        return isEqual;
    }

    public boolean verifyEquals(Object actual, Object expected){
        return checkEquals(actual,expected);
    }

}
