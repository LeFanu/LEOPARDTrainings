package com.leopard.karol.leopardtrainings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/** Author: Karol Pasierb - Software Engineering - 40270305
 * Created by Karol on 2017-03-09.
 *
 * Description:
 * This class contains the code for creating trainings activity
 * On this screen user creates new training plans. Choosing the areas to work with and specific exercises
 *
 * Future updates:
 * - number of routines editbox replaced with number stepper
 *
 * Design Patterns Used:
 *
 * Last Update: 09/03/2017
 */

public class CreateNewRoutine extends AppCompatActivity {

    public  void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    String currentTraineeName;
    Trainee currentTrainee;
    SingletonDBaccess databaseAccess;
    Spinner areaTraining1;
    Spinner areaTraining2;
    Spinner areaTraining3;
    Spinner areaTraining4;

    Spinner timeTraining1;
    Spinner timeTraining2;
    Spinner timeTraining3;
    Spinner timeTraining4;
    //ArrayList<String> mobilizationAreas;
    String[] mobilizationAreas;
    ArrayAdapter<String> adapter;

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
        databaseAccess = SingletonDBaccess.getInstance();
        currentTrainee = databaseAccess.findTrainee(currentTraineeName);
        makeToast(currentTrainee.getName() + " successfully received");
        setContentView(R.layout.create_new_routine_activity);

        mobilizationAreas = databaseAccess.getMobilizationAreas();

        //accessing elements of the layout
        areaTraining1 = (Spinner) findViewById(R.id.areaTraining1);
        areaTraining2 = (Spinner) findViewById(R.id.areaTraining2);
        areaTraining3 = (Spinner) findViewById(R.id.areaTraining3);
        areaTraining4 = (Spinner) findViewById(R.id.areaTraining4);
        timeTraining1 = (Spinner) findViewById(R.id.timeTraining1);
        timeTraining2 = (Spinner) findViewById(R.id.timeTraining2);
        timeTraining3 = (Spinner) findViewById(R.id.timeTraining3);
        timeTraining4 = (Spinner) findViewById(R.id.timeTraining4);


        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mobilizationAreas);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        areaTraining1.setAdapter(adapter);
        areaTraining2.setAdapter(adapter);
        areaTraining3.setAdapter(adapter);
        areaTraining4.setAdapter(adapter);

        String[] trainingTimes = new String[]{"60","90","120"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trainingTimes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeTraining1.setAdapter(adapter);
        timeTraining2.setAdapter(adapter);
        timeTraining3.setAdapter(adapter);
        timeTraining4.setAdapter(adapter);



    }

    public void submitNewRoutine(View view) {
        //accessing elements of the layout
        EditText enterPlanName = (EditText) findViewById(R.id.enterPlanName);
        EditText enterNumberOfRoutines = (EditText) findViewById(R.id.enterNumberOfRoutines);

        String spinnerContent;

        //creating plan and adding it to the Trainee
        TrainingRoutinePlan currentTrainingRoutinePlan = new TrainingRoutinePlan();
        currentTrainingRoutinePlan.setTrainingTitle(enterPlanName.getText().toString());
        currentTrainingRoutinePlan.setRotations(Integer.parseInt(enterNumberOfRoutines.getText().toString()));

        for (int i = 0; i < 4; i++) {
            //getting details of the training
           /* spinnerContent = areaTraining.getSelectedItem().toString();
            Mobilization currentMobilization = new Mobilization();
            currentMobilization.setMobilizationArea(spinnerContent);*/
        }


        currentTrainee.addTrainingRoutine(currentTrainingRoutinePlan);

    }

    public void cancelNewRoutine(View view) {
        Intent cancelCreatingNewRoutine = new Intent(CreateNewRoutine.this, Home.class);
        cancelCreatingNewRoutine.putExtra("Trainee", currentTraineeName);
        startActivity(cancelCreatingNewRoutine);
    }
}
