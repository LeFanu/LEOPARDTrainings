package com.leopard.karol.leopardtrainings;

import android.content.Intent;
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
 * Last Update: 09/03/2017
 */

public class Welcome extends AppCompatActivity implements Serializable{

    //references to all variables and elements used in this activity
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
    Button enterLeopard;
    ArrayAdapter<String> adapter;
    String selectedName;
    Trainee traineeToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        //accessing Edit Boxes
        editTraineeName = (EditText) findViewById(R.id.editTraineeName);
        editTraineeEmail = (EditText) findViewById(R.id.editTraineeEmail);
        //accessing remaining elements
        enterLeopard = (Button) findViewById(R.id.enterLeopard);
        addingNewTrainee = (GridLayout) findViewById(R.id.addingNewTrainee);
        deletingTraineeLayout = (GridLayout) findViewById(R.id.deletingTraineeLayout);
        editExistingTrainee = (GridLayout) findViewById(R.id.editExistingTrainee);
        selectTrainee = (Spinner) findViewById(R.id.selectTrainee);

        //creating or referencing existing logger object
        databaseAccess = SingletonDBaccess.getInstance();
        //reading existing data
        databaseAccess.readDB_onStartup(this);
        //filling the spinner with names from the list of Trainees
        names = databaseAccess.getTraineesNames();


        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        selectTrainee.setAdapter(adapter);
        selectedName = selectTrainee.getSelectedItem().toString();

    }
    protected void onPause(){
        super.onPause();
        //Log.i("Karol", "app paused");
    }
    protected void onStop(){
        super.onStop();
        //Log.i("Karol", "app stopped");
    }

    //ACTIONS FOR ADDING NEW TRAINEE
    public void addingNewTrainee(View view) {
        //if else for button toggle
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
        //adding created Trainee's name to spinner
        names.add(currentTrainee.getName());
        adapter.notifyDataSetChanged();
        //saving our data
        databaseAccess.saveFiles(this);

        //control message
        makeToast("New user created with name " + currentTrainee.getName() + " and  the email " + currentTrainee.getEmail());

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
        //if else for button toggle
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
    public void deleteTraineeSubmit(View view) {
        //getting the name of the trainee to be deleted
        String traineeNameToDeleted =  selectTrainee.getSelectedItem().toString();

        //removing the trainee from the spinner and updating it
        adapter.remove(traineeNameToDeleted);
        adapter.notifyDataSetChanged();
        //removing the trainee from the list and saving data
        databaseAccess.removeTrainee(traineeNameToDeleted);
        databaseAccess.saveFiles(this);

        //control message
        makeToast("User deleted name " + traineeNameToDeleted);

        //recover original view
        enterLeopard.setVisibility(View.VISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }
    public void cancelDelete(View view) {
        enterLeopard.setVisibility(View.VISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }


    //ACTIONS FOR DELETING TRAINEE
    public void editingTrainee(View view) {
        //if else for button toggle
        if(editExistingTrainee.getVisibility() == View.INVISIBLE) {
            editExistingTrainee.setVisibility(View.VISIBLE);
            enterLeopard.setVisibility(View.INVISIBLE);
            //filling the fields with the data for selected Trainee
            selectedName =  selectTrainee.getSelectedItem().toString();
            traineeToEdit = databaseAccess.findTrainee(selectedName);
            editTraineeName.setText(traineeToEdit.getName());
            editTraineeEmail.setText(traineeToEdit.getEmail());
        } else {
            editExistingTrainee.setVisibility(View.INVISIBLE);
            enterLeopard.setVisibility(View.VISIBLE);
        }
        addingNewTrainee.setVisibility(View.INVISIBLE);
        deletingTraineeLayout.setVisibility(View.INVISIBLE);
    }
    public void editTraineeSubmit(View view) {
        //getting the name of the new Trainee and updating its details
        String newTraineeName = editTraineeName.getText().toString();
        traineeToEdit.setName(newTraineeName);
        traineeToEdit.setEmail(editTraineeEmail.getText().toString());

        //updating the spinner with new data
        int position = selectTrainee.getSelectedItemPosition();
        names.set(position,newTraineeName);
        adapter.notifyDataSetChanged();
        //saving app's data
        databaseAccess.saveFiles(this);

        //control message
        makeToast(selectedName + " trainee's name was changed to " + newTraineeName);

        //restoring original view
        editExistingTrainee.setVisibility(View.INVISIBLE);
        enterLeopard.setVisibility(View.VISIBLE);
    }
    public void cancelEditTrainee(View view) {
        enterLeopard.setVisibility(View.VISIBLE);
        editExistingTrainee.setVisibility(View.INVISIBLE);
    }

    //continue with the selected Trainee
    public void enterLeopardHome(View view) {
        //getting the name of selected Trainee to know which object to use and pass this data
        selectedName = selectTrainee.getSelectedItem().toString();
        Intent goToHome = new Intent(Welcome.this, Home.class);
        goToHome.putExtra("Trainee", selectedName);
        startActivity(goToHome);
    }





    public  void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
