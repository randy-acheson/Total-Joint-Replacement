package edu.wpi.total_joint_replacement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.tools.PhysicalAction;


public class PhysicalActionsNewFragment extends BaseFragment {

    public PhysicalActionsNewFragment() {
        title = "Record Activity";
    }

    private PhysicalAction action;
    private TextView name;
    private TextView desc;

    public void setPhysicalAction(PhysicalAction action){
        this.action = action;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_record_new, container, false);

        name = (TextView) view.findViewById(R.id.activity_title);
        desc = (TextView) view.findViewById(R.id.activity_desc);

        //if(action != null){
            //title.setText("New Action");
            //desc.setText("New Description");
            //goal.setText("New Goal");
        //}
        return view;
    }

    public void saveActivity(){
        //PhysicalAction.activities.add(new PhysicalAction(name.getEditableText().toString(), desc.getEditableText().toString(), ""));
        name.setText("");
        desc.setText("");
    }


}
