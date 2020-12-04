package com.zamzamsaefulbahtiar.utsmp.helper;

import java.util.Random;

public class GenerateNumber {
    Random random  = new Random();
    public int getNumber(){

        int number = this.random.nextInt(10000);

        return number;
    }
}
