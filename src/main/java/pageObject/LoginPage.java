package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(name = "txtUsername")
    private WebElement nameTextBox;

    @FindBy(name = "txtPassword")
    private  WebElement passWordTextBox;

    @FindBy(name = "Submit")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage inputUserName(String userName){
        waitForElementVisible(nameTextBox);
       sendKeyToElement(nameTextBox,userName);
       return this;
    }

    public LoginPage inputPassWord(String passWord){
        waitForElementVisible(passWordTextBox);
        sendKeyToElement(passWordTextBox,passWord);
        return this;
    }

    public LoginPage clickLoginButton(){
        waitForElementClickable(loginButton);
        clickToElement(loginButton);
        return this;
    }
}
