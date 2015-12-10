package edu.wpi.total_joint_replacement.entities;

import android.text.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 12/9/2015.
 */
public class PhysicalActions {
    private String title;
    private String description;
    private String goal;


    public PhysicalActions(String title, String description, String goal){
        this.title = title;
        this.description = description;
        this.goal = goal;
    }

    public static List<PhysicalActions> getAllActivities(){
        List<PhysicalActions> activities = new ArrayList<>();
        activities.add(new PhysicalActions("Stair Climbing", "Climb those stairs!", "5 steps"));
        activities.add(new PhysicalActions("Walking", "Walk those paths!", "10 steps"));
        activities.add(new PhysicalActions("Something else", "Do that something else!", "Just do it."));
        return activities;
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
