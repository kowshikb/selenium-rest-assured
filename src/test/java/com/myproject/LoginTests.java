package com.myproject;

import com.myproject.pageObjects.LoginPom;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// import java.time.Duration;

public class LoginTests extends BaseTest {

    private LoginPom loginPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPom(driver);
        loginPage.navigateTo(BASE_URL);
    }

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        // Arrange
        String validUsername = "kowshik.bikkina@skillsoft.com";
        String validPassword = "P@ssw0rd123@";

        // Act
        loginPage.enterUsername(validUsername);
        loginPage.clickNextButton();
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        loginPage.validateHomePage();

    }

}
