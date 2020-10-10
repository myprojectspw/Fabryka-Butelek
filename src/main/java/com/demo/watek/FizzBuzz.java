package com.demo.watek;

public class FizzBuzz {
    public String play(int number) throws IllegalAccessException {
        if(number == 0) throw new IllegalAccessException(("Number is wrong"));
        if(number % 3 == 0) return "Fizz";
        if(number % 5 == 0) return "Buzz";
        if(number % 7 == 0) return "Quzz";
        if(number % 11 == 0) return "Muzz";
        if(number % 13 == 0) return "Luzz";
        if(number % 17 == 0) return "Wizz";
        return String.valueOf(number);
    }
}
