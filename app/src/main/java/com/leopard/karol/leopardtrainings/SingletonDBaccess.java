package com.leopard.karol.leopardtrainings;

import android.content.Context;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/** Author: Karol Pasierb - Software Engineering - 40270305
 * Created by Karol on 2017-03-05.
 *
 * Description:
 * This class contains the code for our Logger class
 * There is no activity assigned to this class.
 * This class is responsible for storing the list of active Trainees.
 * It saves and loads the current data that is used by the user.
 * As each Trainee holds all the user's information this class, with the list of all Trainees,
 * basically stores the whole data of the app
 *
 *
 *  Future updates:
 *
 *
 * Design Patterns Used:
 * I used Singleton Pattern for this logger class. We do not need more than one object to save and load our data
 * Oce it's created we use the same object as our reference variable checks if the constructor was used previously
 *
 * Last Update: 08/03/2017
 */

public class SingletonDBaccess implements Serializable{

    private static final long serialVersionUID = 666L;

    //instance fields with their getters and setters
    private ArrayList<Trainee> trainees = new ArrayList<Trainee>();
    /*public ArrayList<Trainee> getTrainees() {
        return trainees;
    }*/
    public void setTrainees(Trainee trainee) {
        this.trainees.add(trainee);
    }

    private ArrayList<String> traineesNames = new ArrayList<String>();
    public ArrayList<String> getTraineesNames() {
        return traineesNames;
    }
   /* public void setTraineesNames(ArrayList<String> traineesNames) {
        this.traineesNames = traineesNames;
    }*/

    private String[] mobilizationAreas = {"A1 - Neck", "A2 - Posterior Shoulder",
            "A3 - Anterior Shoulder ", "A4 - Arms", "A5  - Trunk","A6 - Posterior High Chain",
            "A7 - Anterior High Chain", "A8 - Medial Chain", "A9 - Posterior Chain", "A10 - Knee",
            "A11 - Medial and Anterior Shin", "A12 - Calf", "A13 - Ankle and Plantar Surface"};
    public String[] getMobilizationAreas() {
        return mobilizationAreas;
    }
    public void setMobilizationAreas(String[] mobilizationAreas) {
        this.mobilizationAreas = mobilizationAreas;
    }

   //static reference for the Singleton Pattern purpose and private constructor
    private static SingletonDBaccess instance;
    private  SingletonDBaccess() {
    }
    //this property will create a new instance for Singleton if it is the first time when it's used. Otherwise it will return the instance of already created object
    public static SingletonDBaccess getInstance() {
        if (instance == null) {
            instance = new SingletonDBaccess();
        }
        return instance;
    }


    //variables for serializing data
    private transient String dbFilename =  "users.bin";
    private transient  FileOutputStream serializerOut;
    private transient FileInputStream serializerIn;
    private transient ObjectInputStream inputSerializer;
    private transient ObjectOutputStream outputSerializer;



    //methods for saving and loading data and using Trainees throghout the application
    public void readDB_onStartup(Context context){
        try {
            serializerIn = context.openFileInput(dbFilename);
            inputSerializer = new ObjectInputStream(serializerIn);
            try {
                trainees = (ArrayList<Trainee>) inputSerializer.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Karol", "Loading trainee error is " + e.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.i("Karol", "Loading trainee error is " + e.toString());
            }
            inputSerializer.close();
            serializerIn.close();
            //Log.i("Karol", "File loaded!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Karol", "Loading error is " + e.toString());
        }

        //Once the data is loaded at the startup we populate the list of the names of Trainees
        for (Trainee trainee: trainees) {
            traineesNames.add(trainee.getName());
        }
    }

    //this method is used after every successful operation performed on the Trainee
    public void saveFiles(Context context){
        try {
            serializerOut = context.openFileOutput(dbFilename, Context.MODE_PRIVATE);
            outputSerializer = new ObjectOutputStream(serializerOut);
            outputSerializer.writeObject(trainees);
            outputSerializer.close();
            serializerOut.close();
           // Log.i("Karol", "File saved!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Karol", "Saving error is " + e.toString());
        }
    }

    //this method removes the Trainee and uses another method to find it
    public void removeTrainee(String name){
        Trainee toDelete = findTrainee(name);
        trainees.remove(toDelete);
    }

    //this method finds the trainee by its name
    public Trainee findTrainee(String name){
        for (Trainee trainee:trainees){
            if (trainee.getName().contentEquals(name)){
                return trainee;
            }
        }
        return null;
    }

}
