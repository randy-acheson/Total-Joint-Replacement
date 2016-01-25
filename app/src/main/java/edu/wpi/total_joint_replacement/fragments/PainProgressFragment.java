package edu.wpi.total_joint_replacement.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.Series;
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.BarGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.tools.Database;
import edu.wpi.total_joint_replacement.tools.Joint;
import edu.wpi.total_joint_replacement.tools.PainEntry;

//import android.widget.NumberPicker;


public class PainProgressFragment extends BaseFragment {

    public PainProgressFragment() {
        title = "Pain Progress";
    }
    private GraphView graph;
    private Calendar cal;
    private Date firstDate;
    private Date lastDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pain_report, container, false);

        graph = (GraphView) view.findViewById(R.id.painGraph);
        cal = Calendar.getInstance();

        Database db = Database.getInstance();
        db.setContext(getActivity().getApplicationContext());

        try {
            db.readDummyData();
        }
        catch (IOException e) {
            Log.d("Exception", "IOException");
        }

        LineGraphSeries<DataPoint> series = createGraph(Joint.BACK);
        graph.addSeries(series);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(firstDate.getTime());
        graph.getViewport().setMaxX(lastDate.getTime());

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);

        return view;
    }

    public LineGraphSeries<DataPoint> createGraph(Joint joint) {
        //ArrayList<PainEntry> painEntries = Database.getInstance().painEntries;
        ArrayList<PainEntry> painEntries = Database.getInstance().getAveragedValues(Database.TimeValue.DAY, joint, 1);

        List<DataPoint> points = new ArrayList<>();

        for (PainEntry entry : painEntries) {

            if (entry.joint == joint) {
                points.add(new DataPoint(entry.time, entry.painLevel));
            }
        }

        firstDate = painEntries.get(0).time;
        lastDate = painEntries.get(painEntries.size() - 1).time;

        DataPoint[] pointArray = new DataPoint[points.size()];
        pointArray = points.toArray(pointArray);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(pointArray);

        return series;
    }

    public void resetGraph(){
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = createGraph(Joint.BACK);
        graph.addSeries(series);
        graph.getViewport().setMinX(firstDate.getTime());
        graph.getViewport().setMaxX(lastDate.getTime());
    }

}
