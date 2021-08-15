package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeeListPage extends AbstractPage {

    @FindBy(id = "btnAdd")
    private WebElement addButton;

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public EmployeeListPage clickToAddButton() {
        clickToElement(addButton);
        return this;
    }


}
