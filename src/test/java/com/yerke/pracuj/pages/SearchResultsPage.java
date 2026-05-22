package com.yerke.pracuj.pages;

import com.yerke.pracuj.hooks.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "[data-test='section-offers'] [data-test='default-offer']")
    private List<WebElement> jobOffers;

    @FindBy(css = "[data-test='section-offers']")
    private WebElement resultsSection;

    @FindBy(css = "[data-test='top-pagination-max-page-number']")
    private WebElement totalPagesElement;

    @FindBy(css = "[data-test='bottom-pagination-button-next']")
    private WebElement nextPageButton;

    public SearchResultsPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isResultsSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(resultsSection));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getJobOffersCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(jobOffers));
        return jobOffers.size();
    }

    public boolean hasJobOffers() {
        return getJobOffersCount() > 0;
    }

    public String getFirstJobTitle() {
        wait.until(ExpectedConditions.visibilityOfAllElements(jobOffers));
        return jobOffers.get(0).getText();
    }

    public boolean isNextPageButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(nextPageButton));
            return nextPageButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}