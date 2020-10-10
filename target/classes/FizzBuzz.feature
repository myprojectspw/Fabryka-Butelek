Feature: FizzBuzz Game play

  Scenario: Play FizzBuzz to get Fizz
    Given Create a FizzBuzz game play
    When I play with number 3
    Then The result was "Fizz"

  Scenario: Play FizzBuzz to get Buzz
    Given Create a FizzBuzz game play
    When I play with number 5
    Then The result was "Buzz"

  Scenario: Play FizzBuzz to get Quzz
    Given Create a FizzBuzz game play
    When I play with number 7
    Then The result was "Quzz"

  Scenario: Play FizzBuzz to get Muzz
    Given Create a FizzBuzz game play
    When I play with number 11
    Then The result was "Muzz"

  Scenario: Play FizzBuzz to get Luzz
    Given Create a FizzBuzz game play
    When I play with number 13
    Then The result was "Luzz"

  Scenario: Play FizzBuzz to get Wizz
    Given Create a FizzBuzz game play
    When I play with number 17
    Then The result was "Wizz"