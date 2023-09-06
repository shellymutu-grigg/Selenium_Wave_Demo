package com.automation.test.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Implementation {
    
    @Given("I am on the {string} page")
    public void iAmOnThePage(String arg0) {
        System.out.println("Hello World!");
    }

    @When("I click on the link {string}")
    public void iClickOnTheLink(String arg0) {
        System.out.println("Hello World!");
    }

    @Then("I should see {string}")
    public void iShouldSee(String arg0) {
        System.out.println("Hello World!");
    }
}
