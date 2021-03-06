package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeeDetailPage extends AbstractPage {

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "middleName")
    private WebElement middleName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "btnSave")
    private WebElement saveButton;

    @FindBy(id = "employeeId")
    private WebElement EmployeeIDAtPersonalForm;

    @FindBy(id = "personal_txtEmployeeId")
    private WebElement employeeIDAtPersonalDetail;

    @FindBy(xpath = "//div[@id='profile-pic']/h1")
    private  WebElement employeeFullNameAtHeader;


    public EmployeeDetailPage(WebDriver driver) {
        super(driver);
    }

    public EmployeeDetailPage inputFirstNameTextBox(String key) {
        sendKeyToElement(firstName, key);
        return this;
    }

    public EmployeeDetailPage inputMiddleNameTextBox(String key) {
        sendKeyToElement(middleName, key);
        return this;
    }

    public EmployeeDetailPage inputLastNameNameTextBox(String key) {
        sendKeyToElement(lastName, key);
        return this;
    }

    public EmployeeDetailPage clickSaveButton() {
        clickToElement(saveButton);
        return this;
    }

    public String getTextEmployeeFullNameAtHeader() {
        return getTextElement(employeeFullNameAtHeader);
    }

    public String getEmployeeIDAtPersonalForm(){
        return getTextElement(EmployeeIDAtPersonalForm);
    }

    public String getEmployeeIDAtPersonalDetail(){
        return getTextElement(employeeIDAtPersonalDetail);
    }

}
