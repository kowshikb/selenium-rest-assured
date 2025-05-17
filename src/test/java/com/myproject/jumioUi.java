package com.myproject;

import com.myproject.pageObjects.LoginPom;
import com.myproject.pageObjects.jumioUIPom;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// import java.time.Duration;

public class jumioUi extends BaseTest {

    private jumioUIPom jumioUIPage;

    @BeforeMethod
    public void setUp() {
        jumioUIPage = new jumioUIPom(driver);
        jumioUIPage.navigateTo(BASE_URL);
    }

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {

    }

}
