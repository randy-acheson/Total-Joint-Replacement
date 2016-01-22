package edu.wpi.total_joint_replacement.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 12/9/2015.
 */
public class Medicine {
    private String name;
    private String dosage;
    private String frequency;

    public static List<Medicine> activities = new ArrayList<>();

    public Medicine(String name, String dosage, String frequency){
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
    }

    public static Medicine newAction = new Medicine("Add a new activity!", "Press this button to add a new activity to your routine.", "");

    public static List<Medicine> getAllActivities(){
        List<Medicine> newList = new ArrayList<>(activities);
        newList.add(new Medicine("Aspirin", "3 pills", "Every morning"));
        newList.add(new Medicine("JointRX", "1 pill", "As needed"));
        newList.add(newAction);
        return newList;
    }


    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

}
