package edu.wpi.total_joint_replacement.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 12/9/2015.
 */
public class PhysicalAction {
    private String title;
    private String description;
    private String goal;

    public static List<PhysicalAction> activities = new ArrayList<>();

    public PhysicalAction(String title, String description, String goal){
        this.title = title;
        this.description = description;
        this.goal = goal;
    }

    public static PhysicalAction newAction = new PhysicalAction("Add a new activity!", "Press this button to add a new activity to your routine.", "");

    public static List<PhysicalAction> getAllActivities(){
        List<PhysicalAction> newList = new ArrayList<>(activities);
        newList.add(new PhysicalAction("Stair Climbing", "Climb those stairs!", "5 steps"));
        newList.add(new PhysicalAction("Walking", "Walk those paths!", ""));
        newList.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        newList.add(newAction);
        return newList;
    }


    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getGoal() {
        return goal;
    }

}
