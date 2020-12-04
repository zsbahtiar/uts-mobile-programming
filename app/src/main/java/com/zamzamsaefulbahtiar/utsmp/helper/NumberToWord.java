package com.zamzamsaefulbahtiar.utsmp.helper;

public class NumberToWord {

    static String word[] = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};

    public static String number(int num){
        return number(Long.valueOf(num));
    }

    public static String number(Long num) {
        if (num < 12) {
            return word[num.intValue()];
        }
        if (num >= 12 && num <= 19) {
            return word[num.intValue() % 10] + " Belas";
        }
        if (num >= 20 && num <= 99) {
            return number(num / 10) + " Puluh " + word[num.intValue() % 10];
        }
        if (num >= 100 && num <= 199) {
            return "Seratus " + number(num % 100);
        }
        if (num >= 200 && num <= 999) {
            return number(num / 100) + " Ratus " + number(num % 100);
        }
        if (num >= 1000 && num <= 1999) {
            return "Seribu " + number(num % 1000);
        }
        if (num >= 2000 && num <= 999999) {
            return number(num / 1000) + " Ribu " + number(num % 1000);
        }
        if (num >= 1000000 && num <= 999999999) {
            return number(num / 1000000) + " Juta " + number(num % 1000000);
        }
        if (num >= 1000000000 && num <= 999999999999L) {
            return number(num / 1000000000) + " Milyar " + number(num % 1000000000);
        }
        if (num >= 1000000000000L && num <= 999999999999999L) {
            return number(num / 1000000000000L) + " Triliun " + number(num % 1000000000000L);
        }
        if (num >= 1000000000000000L && num <= 999999999999999999L) {
            return number(num / 1000000000000000L) + " Quadrilyun " + number(num % 1000000000000000L);
        }

        return "";
    }

}