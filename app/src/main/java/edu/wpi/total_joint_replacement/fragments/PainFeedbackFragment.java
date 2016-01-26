package edu.wpi.total_joint_replacement.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.tools.Database;
import edu.wpi.total_joint_replacement.tools.Joint;
import edu.wpi.total_joint_replacement.tools.PainEntry;

//import android.widget.NumberPicker;


public class PainFeedbackFragment extends BaseFragment {

    public PainFeedbackFragment() {
        title = "Pain Progress";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pain_feedback, container, false);
        return view;
    }

}
