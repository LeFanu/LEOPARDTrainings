package com.leopard.karol.leopardtrainings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/** Author: Karol Pasierb - Software Engineering - 40270305
 * Created by Karol on 2017-03-08.
 *
 * Description:
 * This activity contains the code for all the actions of the welcome screen
 * This is the first screen that user sees when application starts
 * On this screen user can selected trainee profile and continue with the app.
 * Or user may edit, remove or add new trainee profile and then contine using the app.
 *
 *  Future updates:
 *  - add animations for better UI experience
 *  - instead of using additional array of names use the array of trainees
 *
 * Design Patterns Used:
 *
 * Last Update: 09/03/2017
 */

public class Home extends AppCompatActivity {

    public  void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    TextView homeTitle;
    String currentTraineeName;
    Trainee currentTrainee;
    SingletonDBaccess databaseAccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //receiving data from previous activity and making sure it was handled without errors
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                currentTraineeName = null;
            } else {
                currentTraineeName= extras.getString("Trainee");
            }
        } /*else {
            currentTraineeName = (String) savedInstanceState.getSerializable("Trainee");
        }*/
        setContentView(R.layout.home_activity);

        //accessing components
        homeTitle = (TextView) findViewById(R.id.homeTitle);
        databaseAccess = SingletonDBaccess.getInstance();
        currentTrainee = databaseAccess.findTrainee(currentTraineeName);
        makeToast(currentTrainee.getName() + " successfully received");
        //Log.i("Karol", "Name " + currentTraineeName + " successfully received");

        //set the title to match the Trainee's name
        homeTitle.setText("Welcome Leopard \n" + currentTraineeName);
    }
    protected void onPause(){
        super.onPause();
        Log.i("Karol", "app paused");
    }
    protected void onStop(){
        super.onStop();
        Log.i("Karol", "app stopped");
    }

    public void openTodayTraining(View view) {
    }

    public void skipTodayTraining(View view) {
    }

    public void createNewRoutine(View view) {
        Intent startCreateNewRoutine = new Intent(Home.this, CreateNewRoutine.class);
        startCreateNewRoutine.putExtra("Trainee", currentTraineeName);
        startActivity(startCreateNewRoutine);
    }

    public void openStatistics(View view) {
    }
}
