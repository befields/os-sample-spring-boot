package com.example.spring;

import java.util.Random;

public class RandomNameGenerator {

    static String[] FIRST_NAMES = {"Michael", "Jessica", "Christopher", "Ashley", "Matthew",
        "Emily", "Joshua", "Sarah", "Jacob", "Samantha", "Nicholas", "Amanda", "Andrew",
        "Brittany", "Daniel", "Elizabeth", "Tyler", "Taylor", "Joseph", "Megan", "Brandon",
        "Hannah", "David", "Kayla", "James", "Lauren"};

    static String[] LAST_NAMES = {"Gonzalez", "Mason", "Jones", "Murillo", "Bradshaw", "Shaw",
        "Barr", "Mcfarland", "Davenport", "Gallagher", "Chapman", "Hester", "Huynh", "Summers",
        "Johns", "Snow", "Carson", "Richard", "Stein", "Sheppard", "Kidd", "Mcmillan", "Pineda",
        "Clark", "Farley"};

    static Random random = new Random();

    public static String getFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    public static String getLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }
}
