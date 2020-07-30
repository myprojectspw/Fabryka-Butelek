package com.demo.watek;

public class FizzBuzz {
    public String play(int number) throws IllegalAccessException {
        if(number == 0) throw new IllegalAccessException(("Number is wrong"));
        if(number % 3 == 0) return "Fizz";
        if(number % 5 == 0) return "Buzz";

        return String.valueOf(number);
    }
}
