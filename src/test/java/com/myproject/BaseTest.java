package com.myproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static WebDriver driver;
    protected static final String BASE_URL = "https://presales1.percipio.com"; // Replace with your application URL

    @BeforeClass

    public void setupDriver() {

        WebDriverManager.chromedriver().browserVersion("136.0.7103.93").setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        System.out.println("WebDriver initialized successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver closed");
        }
    }

    /**
     * Get the current instance of WebDriver
     * 
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

}