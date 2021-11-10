package ru.tadzh.steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.tadzh.DriverInitializer;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.tadzh.steps.LoginSteps.webDriver;

public class ProviderSteps {

    @When("I navigate to providers.html page")
    public void iNavigateToProviderHtmlPage() {
        webDriver.get(DriverInitializer.getProperty("provider.url"));
    }

    @And("I click on Add Provider button")
    public void iSeeButton() throws InterruptedException {
        Thread.sleep(1000);
        WebElement webElement = webDriver.findElement(By.id("btn-newProvider"));
        webElement.click();
    }


    @And("I provide title as {string}")
    public void iProvideTitleAs(String title) throws InterruptedException {
        Thread.sleep(1000);
        WebElement webElement = webDriver.findElement(By.id("title"));
        webElement.sendKeys(title);
    }

    @And("I click on Submit button")
    public void iClickOnSubmitButton() throws InterruptedException {
        Thread.sleep(1000);
        WebElement webElement = webDriver.findElement(By.id("btn-newProviderSubmit"));
        webElement.click();
    }

    @Then("in table i see provider {string}")
    public void inTableISeeCategory(String name) throws InterruptedException {
        Thread.sleep(1000);
        boolean isContains = checkProvider(name);

        assertTrue(isContains);
    }

    private boolean checkProvider(String title) {
        List<WebElement> elements = webDriver.findElements(By.xpath("//*[@id='providers']/tbody/tr/td[1]"));
        List<String> collect = elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        boolean isContains = false;
        for (String str: collect) {
            if (str.contains(title)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }
}