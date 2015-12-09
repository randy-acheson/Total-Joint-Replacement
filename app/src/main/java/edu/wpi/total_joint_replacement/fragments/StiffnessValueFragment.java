package edu.wpi.total_joint_replacement.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.wpi.total_joint_replacement.R;

//import android.widget.NumberPicker;


public class StiffnessValueFragment extends Fragment {

    public StiffnessValueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stiffness_record, container, false);

        return view;
    }


}
