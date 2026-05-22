package com.yerke.pracuj.steps;

import com.yerke.pracuj.pages.HomePage;
import com.yerke.pracuj.pages.SearchResultsPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SearchSteps {

    private HomePage homePage = new HomePage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Given("I open Pracuj.pl homepage")
    public void iOpenPracujHomepage() {
        homePage.open();
    }

    @And("I accept cookies")
    public void iAcceptCookies() {
        homePage.acceptCookies();
    }

    @When("I search for {string} jobs in {string}")
    public void iSearchForJobsIn(String keyword, String location) {
        homePage.searchJobs(keyword, location);
        homePage.closeModalIfPresent();
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        Assert.assertTrue(
                "Search results section is not visible",
                searchResultsPage.isResultsSectionVisible()
        );
    }

    @And("results count should be greater than 0")
    public void resultsCountShouldBeGreaterThanZero() {
        Assert.assertTrue(
                "No job offers found",
                searchResultsPage.hasJobOffers()
        );
    }

    @And("current URL should contain {string}")
    public void currentUrlShouldContain(String text) {
        Assert.assertTrue(
                "URL does not contain: " + text,
                searchResultsPage.getCurrentUrl().toLowerCase().contains(text.toLowerCase())
        );
    }

    @And("next page button should be visible")
    public void nextPageButtonShouldBeVisible() {
        Assert.assertTrue(
                "Next page button is not visible",
                searchResultsPage.isNextPageButtonVisible()
        );
    }

    @And("I close modal if present")
    public void iCloseModalIfPresent() {
        homePage.closeModalIfPresent();
    }
}