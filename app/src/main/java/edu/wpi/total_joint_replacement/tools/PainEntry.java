package edu.wpi.total_joint_replacement.tools;


import java.util.Date;

/**
 * Created by Randy on 1/23/2016.
 */
public class PainEntry {
    public int subjectID;
    public int painLevel;
    public Joint joint;
    public Date time;

    public PainEntry(int subjectID, int painLevel, Joint joint, Date time) {
        this.subjectID = subjectID;
        this.painLevel = painLevel;
        this.joint = joint;
        this.time = time;
    }
}
