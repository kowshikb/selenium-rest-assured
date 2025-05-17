package com.myproject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class jumioUIPom {

    private WebDriver driver;
    private By usernameLocator = By.id("loginName");
    private By passwordLocator = By.id("password");
    private By nextButtonLocator = By.xpath("//button[@jf-ext-button-ct='next']");
    private By loginButtonLocator = By.xpath("//button[@type='submit']");
    private By libraryButtonLocator = By.xpath("//a[div[text()='Library']]");

    public jumioUIPom(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

}