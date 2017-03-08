package com.leopard.karol.leopardtrainings;

import java.io.Serializable;

/** Author: Karol Pasierb - Software Engineering - 40270305
 * Created by Karol on 2017-03-05.
 *
 * Description:
 * This class contains the code for Trainee
 * There is no activity assigned to this class.
 * Trainee is the user's profile that allows to use the app.
 * All the trainings, routines and all other data in teh app is assigned to a chosen Trainee
 *
 *
 *  Future updates:
 *
 *
 * Design Patterns Used:
 *
 * Last Update: 08/03/2017
 */

public class Trainee implements Serializable {

    //instance fields for user
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    private String currentRoutine;
    public String getCurrentRoutine() {
        return currentRoutine;
    }
    public void setCurrentRoutine(String currentRoutine) {
        this.currentRoutine = currentRoutine;
    }

    private boolean dailyMobilizationCompleted;
    public boolean isDailyMobilizationCompleted() {
        return dailyMobilizationCompleted;
    }
    public void setDailyMobilizationCompleted(boolean dailyMobilizationCompleted) {
        this.dailyMobilizationCompleted = dailyMobilizationCompleted;
    }

    private boolean currentRoutineCompleted;
    public boolean isCurrentRoutineCompleted() {
        return currentRoutineCompleted;
    }
    public void setCurrentRoutineCompleted(boolean currentRoutineCompleted) {
        this.currentRoutineCompleted = currentRoutineCompleted;
    }



    //constructor
    public Trainee() {

    }


}
