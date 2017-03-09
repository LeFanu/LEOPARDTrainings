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
 *
 * Last Update: 08/03/2017
 */

public class SingletonDBaccess implements Serializable{

    private ArrayList<Trainee> trainees = new ArrayList<Trainee>();
    public ArrayList<Trainee> getTrainees() {
        return trainees;
    }
    public void setTrainees(Trainee trainee) {
        this.trainees.add(trainee);
    }

    private ArrayList<String> traineesNames = new ArrayList<String>();
    public ArrayList<String> getTraineesNames() {
        return traineesNames;
    }
    public void setTraineesNames(ArrayList<String> traineesNames) {
        this.traineesNames = traineesNames;
    }


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


    private transient String dbFilename =  "users.bin";
    private transient  FileOutputStream serializerOut;
    private transient FileInputStream serializerIn;
    private transient ObjectInputStream inputSerializer;
    private transient ObjectOutputStream outputSerializer;




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

        //at the startup we populate the list of the names
        for (Trainee trainee: trainees) {
            traineesNames.add(trainee.getName());
        }
    }

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

    public void removeTrainee(String name){
        ///Log.i("Karol", "Trainees are " + trainees.size());
        //Log.i("Karol", "Name is " + name);
        for (Trainee trainee:trainees){
            if (trainee.getName().contentEquals(name)){
                trainees.remove(trainee);
                //Log.i("Karol", "trainee " + name + " removed");
                //Log.i("Karol", "Trainees are " + trainees.size());
                break;
            }
        }
    }

}
