package edu.wpi.total_joint_replacement.fragments;

import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.wpi.total_joint_replacement.R;

//import android.widget.NumberPicker;


public class PainProgressFragment extends BaseFragment {

    public PainProgressFragment() {
        title = "Pain Progress";
    }
    private GraphView graph;
    private Calendar cal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pain_report, container, false);

        graph = (GraphView) view.findViewById(R.id.painGraph);
        cal = Calendar.getInstance();

        Date initialDate = cal.getTime();
        LineGraphSeries<DataPoint> series = createGraph();
        graph.addSeries(series);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(initialDate.getTime());
        graph.getViewport().setMaxX(cal.getTime().getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10); // Why doesn't this work??

        return view;
    }

    public LineGraphSeries<DataPoint> createGraph() {
        Random random = new Random();

        List<DataPoint> points = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            points.add(new DataPoint(cal.getTime(), random.nextInt(11)));
            cal.add( Calendar.DATE, 1 );
        }

        DataPoint[] pointArray = new DataPoint[points.size()];
        pointArray = points.toArray(pointArray);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(pointArray);

        return series;
    }

}
