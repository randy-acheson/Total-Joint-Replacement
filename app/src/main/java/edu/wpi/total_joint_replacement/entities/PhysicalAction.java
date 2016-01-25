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
        /*
        walking (inside or outside; ask separately)
        biking (stationery/inside or outside)
        swimming
        strength and aerobic classes (yoga, dance, etc)
        golfing
        gardening, yard work
        jogging
                */

        List<PhysicalAction> newList = new ArrayList<>(activities);
        newList.add(new PhysicalAction("Walking (Inside)", "Use that treadmill!", "3 miles"));
        newList.add(new PhysicalAction("Walking (Outside)", "Walk those paths!", "3 miles"));
        newList.add(new PhysicalAction("Biking (Inside)", "Use that bike in the gym!", "5 miles"));
        newList.add(new PhysicalAction("Biking (Outside)", "Bike along those paths!", "5 miles"));
        newList.add(new PhysicalAction("Swimming", "Go for a dip!", "5 laps"));
        newList.add(new PhysicalAction("Strength and Aerobic Classes", "Yoga, dance, etc!", "30 minutes"));
        newList.add(new PhysicalAction("Golfing", "Just keep swinging!", "Play for an hour"));
        newList.add(new PhysicalAction("Gardening and Yardwork", "Beautify your landscape!", ""));
        newList.add(new PhysicalAction("Jogging", "Get that heard rate going!", "2 miles"));
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
