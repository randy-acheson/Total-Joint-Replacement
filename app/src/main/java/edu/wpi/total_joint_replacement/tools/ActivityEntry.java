package edu.wpi.total_joint_replacement.tools;

import java.util.Date;

/**
 * Created by Randy on 2/8/2016.
 */
public class ActivityEntry {
    public int key;
    public int duration;
    public Date time;

    public ActivityEntry(int key, int duration, Date time) {
        this.key = key;
        this.duration = duration;
        this.time = time;
    }
}
