package edu.wpi.total_joint_replacement.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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


public class PainProgressFragment extends BaseFragment {

    public PainProgressFragment() {
        title = "Pain Progress";
    }
    private GraphView graph;
    private Calendar cal;
    private long firstDate = new Date().getTime();
    private long lastDate;
    private Database.TimeValue currentTimeAverage = Database.TimeValue.DAY;


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

        makeSeries(currentTimeAverage);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);

        return view;
    }

    public LineGraphSeries<DataPoint> createGraph(Joint joint, Database.TimeValue timeSetting) {
        //ArrayList<PainEntry> painEntries = Database.getInstance().painEntries;
        ArrayList<PainEntry> painEntries = Database.getInstance().getAveragedValues(timeSetting, joint, 1);

        List<DataPoint> points = new ArrayList<>();

        for (PainEntry entry : painEntries) {

            if (entry.joint == joint) {
                points.add(new DataPoint(entry.time, entry.painLevel));
            }
        }
        firstDate = Math.min(firstDate, painEntries.get(0).time.getTime());
        lastDate = Math.max(lastDate, painEntries.get(painEntries.size() - 1).time.getTime());

        DataPoint[] pointArray = new DataPoint[points.size()];
        pointArray = points.toArray(pointArray);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(pointArray);

        return series;
    }

    public void resetGraph(){
        /*graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = createGraph(Joint.BACK);
        graph.addSeries(series);
        graph.getViewport().setMinX(firstDate.getTime());
        graph.getViewport().setMaxX(lastDate.getTime());*/
        graph.removeAllSeries();
        makeSeries(currentTimeAverage);
    }

    public void makeSeries(Database.TimeValue timeSetting){
        currentTimeAverage = timeSetting;

        LineGraphSeries<DataPoint> series = createGraph(Joint.BACK, currentTimeAverage);
        series.setTitle("Back");
        series.setColor(Color.BLUE);

        /*LineGraphSeries<DataPoint> series2 = createGraph(Joint.RIGHT_HIP, currentTimeAverage);
        series2.setTitle("Right Hip");
        series2.setColor(Color.GREEN);
        graph.addSeries(series2);*/

        LineGraphSeries<DataPoint> series3 = createGraph(Joint.RIGHT_KNEE, currentTimeAverage);
        series3.setTitle("Right Knee");
        series3.setColor(Color.RED);
        graph.getViewport().setMinX(firstDate);
        graph.getViewport().setMaxX(lastDate);


        //generateBackground(10.0, Color.argb(41, 193, 66, 66));
        //generateBackground(7.5, Color.argb(41, 198, 206, 37));
        //generateBackground(2.5, Color.argb(41, 80, 191, 63));


        graph.addSeries(series);
        graph.addSeries(series3);
    }

    private void generateBackground(double lineHeight, int color) {
        List<DataPoint> pointsbelow2 = new ArrayList<>();
        pointsbelow2.add(new DataPoint(firstDate - 1000000, lineHeight));
        pointsbelow2.add(new DataPoint(lastDate + 1000000, lineHeight));
        DataPoint[] pointArray = new DataPoint[pointsbelow2.size()];
        pointArray = pointsbelow2.toArray(pointArray);

        LineGraphSeries<DataPoint> lowerLineSeries = new LineGraphSeries<>(pointArray);
        lowerLineSeries.setBackgroundColor(color);
        lowerLineSeries.setColor(color);
        lowerLineSeries.setDrawBackground(true);
        graph.addSeries(lowerLineSeries);
    }

}
