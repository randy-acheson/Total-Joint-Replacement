package edu.wpi.total_joint_replacement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.wpi.total_joint_replacement.R;



public class PainValueFragment extends BaseFragment {

    public PainValueFragment() {
        title = "Record Pain";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pain_record, container, false);

        return view;
    }


}
