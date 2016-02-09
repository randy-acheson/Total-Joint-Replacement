package edu.wpi.total_joint_replacement.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 12/9/2015.
 */
public class PhysicalAction {
    private int key;
    private String title;
    private String description;
    private String goal;


    public static List<PhysicalAction> activities = new ArrayList<>();

    public PhysicalAction(int key, String title, String description, String goal){
        this.key = key;
        this.title = title;
        this.description = description;
        this.goal = goal;
    }

    public int getKey() {
        return key;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() { return description; }
    public String getGoal() {
        return goal;
    }

}
