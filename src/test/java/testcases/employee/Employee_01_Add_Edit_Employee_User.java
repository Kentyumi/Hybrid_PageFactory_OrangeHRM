package testcases.employee;

import commons.AbstractTest;
import commons.DataFaker;
import driverFacTory.DriverFactory;
import driverFacTory.DriverManager;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;
import pageObject.*;

import java.util.concurrent.TimeUnit;

public class Employee_01_Add_Edit_Employee_User extends AbstractTest {

    WebDriver driver;
    DriverManager driverManager;
    EmployeeDetailPage employeeDetailPage;
    UserDetailPage userDetail;
    DashBoardPage dashBoardPage;
    LoginPage loginPage;
    EmployeeListPage employeeListPage;

    String firstName, lastName, employeeId;
    DataFaker data;


    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlValue) {
        driverManager = DriverFactory.getBrowserDriver(browserName);
        driver = driverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(urlValue);
        data = DataFaker.getData();

        firstName = data.getFirstName();
        lastName = data.getLastName();

        log.info("Pre-condition : Step 01 :  Input to Username and PassWord Text box ");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUserName("admin").inputPassWord("admin123");

        log.info("Pre-condition : Step 02 : Click Login button ");
        loginPage.clickLoginButton();
    }


    @Test
    public void Employee_01_Add_Employee() {
        dashBoardPage = new DashBoardPage(driver);
        dashBoardPage.openPimTab().clickEmployeeList();

        employeeListPage = new EmployeeListPage(driver);
        employeeListPage.clickToAddButton();

        employeeDetailPage = new EmployeeDetailPage(driver);
        employeeDetailPage.inputFirstNameTextBox(firstName).inputLastNameNameTextBox(lastName);
        employeeId = employeeDetailPage.getEmployeeIDAtPersonalForm();
        employeeDetailPage.clickSaveButton();

        String employeeFullNameAtHeader = employeeDetailPage.getTextEmployeeFullNameAtHeader();
        String employeeIDAtPersonalDetail = employeeDetailPage.getEmployeeIDAtPersonalDetail();
        verifyEquals(employeeFullNameAtHeader,firstName+" "+lastName);
        verifyEquals(employeeId,employeeIDAtPersonalDetail);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}


