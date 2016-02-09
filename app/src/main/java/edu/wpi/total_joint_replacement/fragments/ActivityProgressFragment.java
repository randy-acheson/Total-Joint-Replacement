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
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.tools.ActivityEntry;
import edu.wpi.total_joint_replacement.tools.Database;
import edu.wpi.total_joint_replacement.tools.Joint;
import edu.wpi.total_joint_replacement.tools.PainEntry;


public class ActivityProgressFragment extends BaseFragment {

    public ActivityProgressFragment() {
        title = "Activity Progress";
    }
    private GraphView graph;
    private Calendar cal;
    private Date firstDate;
    private Date lastDate;
    private Database.TimeValue currentTimeAverage = Database.TimeValue.DAY;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_report, container, false);

        graph = (GraphView) view.findViewById(R.id.activityGraph);
        cal = Calendar.getInstance();

        /*Database db = Database.getInstance();
        db.setContext(getActivity().getApplicationContext());

        try {
            db.readActivityDummyData();
        }
        catch (IOException e) {
            Log.d("Exception", "IOException");
        }*/

        makeSeries(currentTimeAverage);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);

        return view;
    }

    public BarGraphSeries<DataPoint> createGraph() {
        ArrayList<ActivityEntry> activityEntries = Database.getInstance().activityEntries;

        List<DataPoint> points = new ArrayList<>();

        for (ActivityEntry entry : activityEntries) {
            points.add(new DataPoint(entry.time, entry.duration));
        }

        firstDate = activityEntries.get(0).time;
        lastDate = activityEntries.get(activityEntries.size() - 1).time;

        DataPoint[] pointArray = new DataPoint[points.size()];
        pointArray = points.toArray(pointArray);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(pointArray);

        /*BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });*/

        return series;
    }

    public void resetGraph() {
        //graph.removeAllSeries();
        //makeSeries(currentTimeAverage);
    }

    public void makeSeries(Database.TimeValue timeSetting) {
        currentTimeAverage = timeSetting;

        BarGraphSeries<DataPoint> series = createGraph();
        series.setTitle("Activity");
        series.setColor(Color.BLUE);
        graph.addSeries(series);
        //graph.getViewport().setXAxisBoundsManual(false);
        //graph.getViewport().setMinX(firstDate.getTime());
        //graph.getViewport().setMaxX(lastDate.getTime());
    }
}
