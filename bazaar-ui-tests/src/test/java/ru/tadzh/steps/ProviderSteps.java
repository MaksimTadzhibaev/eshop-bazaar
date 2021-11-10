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

    @Then("I see button {string}")
    public void iSeeButton(String btn_text) throws InterruptedException {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/a"));
        assertThat(webElement.getText()).isEqualTo(btn_text);
    }

    @And("I click on Add Provider button")
    public void iClickOnAddProviderButton() throws InterruptedException {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/a"));
        webElement.click();
    }

    @And("I provide Name as {string}")
    public void iProvideTitleAs(String name) throws InterruptedException {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(name);
        webElement = webDriver.findElement(By.id("cost"));
    }

    @And("I click on Submit button")
    public void iClickOnSubmitButton() throws InterruptedException {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        webElement.click();
    }

    @Then("in table i see provider {string}")
    public void inTableISeeCategory(String name) throws InterruptedException {
        Thread.sleep(3000);
        boolean isContains = checkProvider(name);

        assertTrue(isContains);
    }

    private boolean checkProvider(String name) {
        List<WebElement> elements = webDriver.findElements(By.xpath("/html/body/div[2]/div/div[3]/table"));
        List<String> collect = elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        boolean isContains = false;
        for (String str: collect) {
            if (str.contains(name)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }
}