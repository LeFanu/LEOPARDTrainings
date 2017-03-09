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
 *  - instead of using additional array of names use the array of trainees
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
    GridLayout editExistingTrainee;
    EditText editTraineeName;
    EditText editTraineeEmail;
    ArrayList<String> names;
    //ArrayList<Trainee> trainees;
    Button enterLeopard;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        enterLeopard = (Button) findViewById(R.id.enterLeopard);
        addingNewTrainee = (GridLayout) findViewById(R.id.addingNewTrainee);
        deletingTraineeLayout = (GridLayout) findViewById(R.id.deletingTraineeLayout);
        editExistingTrainee = (GridLayout) findViewById(R.id.editExistingTrainee);
        selectTrainee = (Spinner) findViewById(R.id.selectTrainee);


        databaseAccess = SingletonDBaccess.getInstance();
        databaseAccess.readDB_onStartup(this);
        names = databaseAccess.getTraineesNames();


        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        selectTrainee.setAdapter(adapter);
        //Log.i("Karol", "selected item position " + selectTrainee.getSelectedItemPosition());
    }

    protected void onPause(){
        super.onPause();

        Log.i("Karol", "app paused");
    }

    protected void onStop(){
        super.onStop();

        Log.i("Karol", "app stopped");
    }

    //ACTIONS FOR ADDING NEW TRAINEE
    public void addingNewTrainee(View view) {
        if(addingNewTrainee.getVisibility() == View.INVISIBLE) {
            addingNewTrainee.setVisibility(View.VISIBLE);
            enterLeopard.setVisibility(View.INVISIBLE);
        } else {
            addingNewTrainee.setVisibility(View.INVISIBLE);
            enterLeopard.setVisibility(View.VISIBLE);
        }
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
        editExistingTrainee.setVisibility(View.INVISIBLE);

    }

    public void addNewTraineeSubmit(View view) {
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
        adapter.notifyDataSetChanged();

        //control messages
        makeToast("New user created with name " + currentTrainee.getName() + " and  the email " + currentTrainee.getEmail());
       // makeToast("Our trainees " + databaseAccess.getTrainees().toString());

        //recover original view
        enterNewTraineeName.setText("");
        enterTraineeEmail.setText("");
        addingNewTrainee.setVisibility(View.INVISIBLE);
        enterLeopard.setVisibility(View.VISIBLE);
    }

    public void cancelAddTrainee(View view) {
        enterLeopard.setVisibility(View.VISIBLE);
        addingNewTrainee.setVisibility(View.INVISIBLE);
    }



    //ACTIONS FOR DELETING TRAINEE
    public void deleteTrainee(View view) {
        if(deletingTraineeLayout.getVisibility() == View.INVISIBLE) {
            deletingTraineeLayout.setVisibility(View.VISIBLE);
            enterLeopard.setVisibility(View.INVISIBLE);
        } else {
            deletingTraineeLayout.setVisibility(View.INVISIBLE);
            enterLeopard.setVisibility(View.VISIBLE);
        }
        addingNewTrainee.setVisibility(View.INVISIBLE);
        editExistingTrainee.setVisibility(View.INVISIBLE);
    }

    public void cancelDelete(View view) {
        enterLeopard.setVisibility(View.VISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }

    public void deleteTraineeSubmit(View view) {
        //getting the name of teh trainee to be deleted
        String traineeNameToDeleted =  selectTrainee.getSelectedItem().toString();

        //removing the trainee from the list
        databaseAccess.removeTrainee(traineeNameToDeleted);
        //removing the trainee from the spinner and updating it
        adapter.remove(traineeNameToDeleted);
        adapter.notifyDataSetChanged();
        //saving our app's data
        databaseAccess.saveFiles(this);

        makeToast("User deleted name " + traineeNameToDeleted);

        //recover original view
        enterLeopard.setVisibility(View.VISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }


    //ACTIONS FOR DELETING TRAINEE
    public void editingTrainee(View view) {
        if(editExistingTrainee.getVisibility() == View.INVISIBLE) {
            editExistingTrainee.setVisibility(View.VISIBLE);
            enterLeopard.setVisibility(View.INVISIBLE);
        } else {
            editExistingTrainee.setVisibility(View.INVISIBLE);
            enterLeopard.setVisibility(View.VISIBLE);
        }
        addingNewTrainee.setVisibility(View.INVISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }

    public void editTraineeSubmit(View view) {

    }

    public void cancelEditTrainee(View view) {
        enterLeopard.setVisibility(View.VISIBLE);
        editExistingTrainee.setVisibility(View.INVISIBLE);
    }

    public void enterLeopardHome(View view) {
    }





    public  void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
