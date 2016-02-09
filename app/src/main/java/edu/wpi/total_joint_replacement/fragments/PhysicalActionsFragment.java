package edu.wpi.total_joint_replacement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.tools.PhysicalAction;


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

        return view;
    }


}
