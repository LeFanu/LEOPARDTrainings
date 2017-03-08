package com.leopard.karol.leopardtrainings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/** Author: Karol Pasierb - Software Engineering - 40270305
 * Created by Karol on 2017-03-05.
 *
 * Description:
 * This activity contains the code for all the actions of the welcome screen
 * This is the first screen that user sees when application starts
 * On this screen user can selected trainee profile and continue with the app.
 * Or user may edit, remove or add new trainee profile and then contine using the app.
 *
 *  Future updates:
 *  - add animations for better UI experience
 *
 * Design Patterns Used:
 *
 * Last Update: 08/03/2017
 */

public class Welcome extends AppCompatActivity implements Serializable{

    SingletonDBaccess databaseAccess;
    Spinner selectTrainee;
    GridLayout addingNewTrainee;
    EditText enterNewTraineeName;
    EditText enterTraineeEmail;
    GridLayout deletingTraineeLayout;
    ArrayList<String> names;
    Button enterLeopard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        enterLeopard = (Button) findViewById(R.id.enterLeopard);
        addingNewTrainee = (GridLayout) findViewById(R.id.addingNewTrainee);
        deletingTraineeLayout = (GridLayout) findViewById(R.id.deletingTraineeLayout);
        //addingNewTrainee.setVisibility(View.INVISIBLE);
        databaseAccess = SingletonDBaccess.getInstance();

        databaseAccess.readDB_onStartup(this);

        selectTrainee = (Spinner) findViewById(R.id.selectTrainee);
        names = databaseAccess.getTraineesNames();
        Log.i("Karol", "I'm going to read names  ");
        for (String name: names){
            Log.i("Karol", "Names are : " + name);
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        selectTrainee.setAdapter(adapter);
    }

    public void addingNewTrainee(View view) {
        addingNewTrainee.setVisibility(View.VISIBLE);
        enterLeopard.setVisibility(View.INVISIBLE);
    }

    public void addNewTraineeSubmit(View view) {
        Log.i("Karol", "I'm in adding new trainee  ");
        //accessing Edit Boxes
        enterNewTraineeName = (EditText) findViewById(R.id.enterNewTraineeName);
        enterTraineeEmail = (EditText) findViewById(R.id.enterTraineeEmail);

        //Creating new trainee with entered details
        Trainee currentTrainee = new Trainee();
        currentTrainee.setName(enterNewTraineeName.getText().toString());
        currentTrainee.setEmail(enterTraineeEmail.getText().toString());
        databaseAccess.setTrainees(currentTrainee);
        names.add(currentTrainee.getName());
        databaseAccess.saveFiles(this);


        //control messages
        makeToast("New user created with name " + currentTrainee.getName() + " and  the email " + currentTrainee.getEmail());
       // makeToast("Our trainees " + databaseAccess.getTrainees().toString());

        //recover original view
        enterNewTraineeName.setText("");
        enterTraineeEmail.setText("");
        addingNewTrainee.setVisibility(View.INVISIBLE);
        enterLeopard.setVisibility(View.VISIBLE);
    }



    public  void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void deleteTrainee(View view) {

        enterLeopard.setVisibility(View.INVISIBLE);
        deletingTraineeLayout.setVisibility(View.VISIBLE);
    }

    public void cancelDelete(View view) {
        enterLeopard.setVisibility(View.VISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }

    public void deleteTraineeSubmit(View view) {
        Log.i("Karol", "I'm in delete user  ");
        Trainee currentTrainee;

        selectTrainee.getSelectedItem();
        Log.i("Karol", "Selected item is:  " + selectTrainee.getSelectedItem());
        databaseAccess.saveFiles(this);


        //recover original view
        enterLeopard.setVisibility(View.VISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }
}
