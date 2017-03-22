package com.leopard.karol.leopardtrainings;

import java.util.ArrayList;

/**
 * Created by Karol on 2017-03-09.
 */

public class TrainingRoutinePlan {

    //instance fields with getters and setters
    private String trainingTitle;
    public String getTrainingTitle() {
        return trainingTitle;
    }
    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }

    private int rotations;
    public int getRotations() {
        return rotations;
    }
    public void setRotations(int rotations) {
        this.rotations = rotations;
    }

    private int daysPassed;
    public int getDaysPassed() {
        return daysPassed;
    }
    public void setDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    private int daysRemaining;
    public int getDaysRemaining() {
        return daysRemaining;
    }
    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    private ArrayList<Mobilization> mobilizations = new ArrayList<Mobilization>();
    public ArrayList<Mobilization> getMobilizations() {
        return mobilizations;
    }
    public void setMobilizations(ArrayList<Mobilization> mobilizations) {
        this.mobilizations = mobilizations;
    }
}
