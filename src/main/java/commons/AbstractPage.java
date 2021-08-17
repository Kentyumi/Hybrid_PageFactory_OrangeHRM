package commons;


import com.oracle.tools.packager.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;

public abstract class AbstractPage {

    private JavascriptExecutor jsExecutor;

    private Alert alert;
    private Select select;
    private final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Function for browser

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }


    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void fordWardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void acceptAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.accept();
    }


    public void cancelAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getAlertText(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void sendKeyToAlert(WebDriver driver, String text) {
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    // Function for Java Execute

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }


    // Function for Element

    public void clickToElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    public void sendKeyToElement(WebElement element, String key) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(key);
    }

    public String getTextElement(WebElement element) {
        waitForElementVisible(element);
        return element.getText();
    }

    public void verifyTrue(boolean isTrue) {
        Assert.assertTrue(isTrue);
    }

    public void verifyEqual(String s1, String s2) {
        Assert.assertEquals(s1, s2);
    }

    public void selectItemInDropDown(WebElement element, String itemValue) {
        select = new Select(element);
        select.selectByVisibleText(itemValue);
    }

    public String getSelectedItemInDropDown(WebElement element, String xpathValue) {
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropDownMultiple(WebElement element, String xpathValue) {
        select = new Select(element);
        return select.isMultiple();
    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isElementDisplayed(WebElement element, int timeOutInSecond) {
        boolean isVisible = false;
        try {
            WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecond);
            if (explicitWait.until(ExpectedConditions.visibilityOf(element)) != null) {
                isVisible = true;
            }
        } catch (Exception e) {
            Log.info(element.getText() + "is not displayed");
        }
        return isVisible;
    }

    private boolean isForElementPresent(WebElement element) {
        return isElementDisplayed(element, Constant.TIME_OUT_MEDIUM_ELEMENT);
    }

    public void waitForElementClickable(WebElement element) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Constant.TIME_OUT_MEDIUM_ELEMENT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementVisible(WebElement element) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Constant.TIME_OUT_NORMAL_ELEMENT);
        explicitWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementInvisible(WebElement element) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Constant.TIME_OUT_MEDIUM_ELEMENT);
        explicitWait.until(ExpectedConditions.invisibilityOf(element));
    }

    // Random using Java

    // Random from 100 to 999
    public static int getRandomNumber() {
        int max = 999;
        int min = 100;
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    // Random from min to max

    public static int getRandomNumber(int max, int min) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    // Get random number by date time

    public static long getRandomNumberByDateTime() {
        return Calendar.getInstance().getTimeInMillis() % 100000;
    }

    // Get random mail

    public static String getRandomMail() {
        return "automation" + getRandomNumberByDateTime() + "@gmail.com";
    }
}