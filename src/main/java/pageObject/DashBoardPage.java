package pageObject;


import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardPage extends AbstractPage {

    @FindBy(id = "menu_pim_viewPimModule")
    private WebElement pimTab;

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public DashBoardPage openPimTab() {
        clickToElement(pimTab);
        return this;
    }
}
