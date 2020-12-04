package com.zamzamsaefulbahtiar.utsmp.helper;


public class Validation {


    public static boolean terbilangAnswer(String terbilang, int number){
        String answerTrue = NumberToWord.number(number).trim().toLowerCase();
        return answerTrue.equals(terbilang.toLowerCase());
    }
}
