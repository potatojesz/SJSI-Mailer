package org.sjsi.service.model;

import org.sjsi.service.enums.Gender;

public class GenderApiModel {
    private String name;
    private Gender gender;
    private double probability;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getProbability() {
        return probability;
    }
    public void setProbability(double probability) {
        this.probability = probability;
    }
}
