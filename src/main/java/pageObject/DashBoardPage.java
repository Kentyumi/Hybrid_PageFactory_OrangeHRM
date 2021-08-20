package pageObject;


import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardPage extends AbstractPage {

    @FindBy(xpath = "//b[contains(text(),'PIM')]")
    private WebElement pimTab;

    @FindBy(id="menu_pim_viewEmployeeList")
    private  WebElement employeeList;

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public DashBoardPage openPimTab() {
        clickToElement(pimTab);
        return this;
    }

    public DashBoardPage clickEmployeeList(){
        clickToElement(employeeList);
        return this;
    }
}
