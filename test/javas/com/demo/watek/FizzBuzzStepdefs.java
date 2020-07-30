package com.demo.watek;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class FizzBuzzStepdefs {

    FizzBuzz fizzBuzz;
    String result;

    @Given("^Create a FizzBuzz game play$")
    public void createAFizzBuzzGamePlay() throws Throwable {
        fizzBuzz = new FizzBuzz();
    }

    @When("^I play with number (\\d+)$")
    public void iPlayWithNumber(int number) throws Throwable {
        result = fizzBuzz.play(number);
    }


    @Then("^The result was Fizz$")
    public void theResultWasFizz() throws Throwable {
        assertEquals(result, "Fizz");
    }

    @Then("^The result was \"([^\"]*)\"$")
    public void theResultWas(String arg0) throws Throwable {
        assertEquals(result, arg0);
    }
}
