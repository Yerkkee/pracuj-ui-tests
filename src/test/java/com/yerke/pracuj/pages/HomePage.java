package com.yerke.pracuj.pages;

import com.yerke.pracuj.hooks.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String URL = "https://www.pracuj.pl";

    @FindBy(css = "[data-test='input-kw'] input[data-test='input-field']")
    private WebElement searchKeywordInput;

    @FindBy(css = "[data-test='input-wp'] input[data-test='input-field']")
    private WebElement searchLocationInput;

    @FindBy(css = "[data-test='search-button']")
    private WebElement searchButton;

    @FindBy(css = "[data-test='button-submitCookie']")
    private WebElement cookieAcceptButton;

    @FindBy(css = "button[aria-label='Zamknij']")
    private WebElement modalCloseButton;

    public HomePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
        waitForCloudflare();
        simulateHumanMouseMovement();
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
        }
    }

    public void enterKeyword(String keyword) {
        humanDelay();
        wait.until(ExpectedConditions.elementToBeClickable(searchKeywordInput));
        searchKeywordInput.clear();
        humanDelay();
        for (char c : keyword.toCharArray()) {
            searchKeywordInput.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(50 + new Random().nextInt(100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void humanDelay() {
        try {
            Thread.sleep(500 + new Random().nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void enterLocation(String location) {
        wait.until(ExpectedConditions.elementToBeClickable(searchLocationInput));
        searchLocationInput.clear();
        searchLocationInput.sendKeys(location);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void searchJobs(String keyword, String location) {
        enterKeyword(keyword);
        enterLocation(location);
        clickSearch();
    }

    public void closeModalIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton)).click();
        } catch (Exception e) {
            // modal not appeared - that's ok
        }
    }

    public void waitForCloudflare() {
        try {
            Thread.sleep(3000); // дать время Cloudflare проверить
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simulateHumanMouseMovement() {
        try {
            Actions actions = new Actions(driver);
            Random random = new Random();

            // двигаем мышь по случайной траектории
            for (int i = 0; i < 5; i++) {
                int x = random.nextInt(200) - 100;
                int y = random.nextInt(200) - 100;
                actions.moveByOffset(x, y).perform();
                Thread.sleep(300 + random.nextInt(400));
            }

            // медленно двигаемся к центру страницы
            actions.moveByOffset(0, 0).perform();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            // движение не сработало - продолжаем
        }
    }
}