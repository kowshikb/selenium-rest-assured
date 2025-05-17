package com.myproject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPom {
    private WebDriver driver;
    private By usernameLocator = By.id("loginName");
    private By passwordLocator = By.id("password");
    private By nextButtonLocator = By.xpath("//button[@jf-ext-button-ct='next']");
    private By loginButtonLocator = By.xpath("//button[@type='submit']");
    private By libraryButtonLocator = By.xpath("//a[div[text()='Library']]");

    public LoginPom(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Wait for an element to be clickable and then click it
     */
    public void waitForAndClick(By locator, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        // First check visibility, then clickability
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Check if an element is displayed within the given timeout
     */
    public boolean isElementDisplayed(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Element not displayed or found within " + timeoutInSeconds +
                    " seconds: " + e.getMessage() + " for locator: " + locator);
            return false;
        }
    }

    /**
     * Navigate to the given URL
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Enter text into a field if it exists
     */
    private void enterText(By locator, String text) {
        if (isElementDisplayed(locator, 30)) {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }

    /**
     * Enter username in the username field
     */
    public void enterUsername(String username) {
        enterText(usernameLocator, username);
    }

    /**
     * Enter password in the password field
     */
    public void enterPassword(String password) {
        enterText(passwordLocator, password);
    }

    /**
     * Click the login button
     */
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    /**
     * Click the next button if it's visible
     */
    public void clickNextButton() {
        if (isElementDisplayed(nextButtonLocator, 10)) {
            waitForAndClick(nextButtonLocator, 10);
        } else {
            System.out.println("Next button not displayed");
        }
    }

    /**
     * Validate that the user is on the home page
     */
    public void validateHomePage() {
        if (isElementDisplayed(libraryButtonLocator, 30)) {
            System.out.println("Library button is displayed so user is on home page");
        } else {
            System.out.println("Library button not displayed");
        }
    }
}