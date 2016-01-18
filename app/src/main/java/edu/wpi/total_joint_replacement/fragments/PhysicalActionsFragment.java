package edu.wpi.total_joint_replacement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.entities.PhysicalAction;


public class PhysicalActionsFragment extends BaseFragment {

    public PhysicalActionsFragment() {
        title = "Record Activity";
    }

    PhysicalAction action;

    public void setPhysicalAction(PhysicalAction action){
        this.action = action;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_record, container, false);

        TextView title = (TextView) view.findViewById(R.id.activity_title);
        TextView desc = (TextView) view.findViewById(R.id.activity_desc);
        TextView goal = (TextView) view.findViewById(R.id.activity_goal);

        if(action != null){
            title.setText(action.getTitle());
            desc.setText("Description: " + action.getDescription());
            if(!action.getGoal().isEmpty()) {
                goal.setText("Goal: " + action.getGoal());
            }
            else{
                goal.setText("Click here to set a goal!");
            }
        }
        return view;
    }


}
