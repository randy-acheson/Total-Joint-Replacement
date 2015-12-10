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


    public PhysicalAction(String title, String description, String goal){
        this.title = title;
        this.description = description;
        this.goal = goal;
    }

    public static List<PhysicalAction> getAllActivities(){
        List<PhysicalAction> activities = new ArrayList<>();
        activities.add(new PhysicalAction("Stair Climbing", "Climb those stairs!", "5 steps"));
        activities.add(new PhysicalAction("Walking", "Walk those paths!", "10 steps"));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
        activities.add(new PhysicalAction("Something else", "Do that something else!", "Just do it."));
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
