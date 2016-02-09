package edu.wpi.total_joint_replacement.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.activities.RecordActivityActivity;
import edu.wpi.total_joint_replacement.tools.ActivityEntry;
import edu.wpi.total_joint_replacement.tools.Database;
import edu.wpi.total_joint_replacement.tools.PainEntry;
import edu.wpi.total_joint_replacement.tools.PhysicalAction;
import edu.wpi.total_joint_replacement.tools.TextListAdapter;


public class ActivityListFragment extends BaseFragment {

    public ActivityListFragment() {
        title = "Record Activity";
    }
    private RecordActivityActivity recordActivity;
    private ActivityProgressFragment progressFragment;

    List<PhysicalAction> actionList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_record_main, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.activitiesList);

        Activity activity = getActivity();
        if(activity != null){
            if(activity instanceof RecordActivityActivity){
                recordActivity = (RecordActivityActivity) activity;
            }
        }

        createAllActivities();

        // Defined Array values to show in ListView
        ArrayList<TextView> views = new ArrayList<>();
        for (int i = 0; i < actionList.size(); i++) {
            TextView newView = new TextView(view.getContext());
            newView.setText(getActivityHTML(actionList.get(i)));
            views.add(newView);
        }

        TextListAdapter adapter = new TextListAdapter(view.getContext(), android.R.layout.simple_list_item_1, views);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                if (recordActivity != null) {
                    enterActivityValue(actionList.get(itemPosition));
                    //recordActivity.getPager().setCurrentItem(itemPosition + 1);
                }
            }

        });

        return view;
    }

    public void enterActivityValue(final PhysicalAction action) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        final AlertDialog dialog;

        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_activity_record, null);

        final EditText durationText = (EditText) v.findViewById(R.id.etRecordValue);

        if(action != null){
            alert.setTitle(action.getTitle());
        }

        alert.setView(v);
        alert.setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (durationText.getText().toString().trim().length() > 0) {
                            int duration = Integer.valueOf((durationText).getText().toString());
                            Database.getInstance().activityEntries.add(
                                    new ActivityEntry(action.getKey(), duration, new Date()));

                            Toast.makeText(getActivity(),
                                    getResources().getString(R.string.activity_submit_success),
                                    Toast.LENGTH_SHORT).show();
                            progressFragment.resetGraph();
                        }
                        else {
                            Toast.makeText(getActivity(),
                                    getResources().getString(R.string.activity_submit_failure),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //udc.writeToSDFile("Survey 1 Cancelled");
            }
        });

        dialog = alert.create();
        dialog.show();
    }

    private String getActivityHTML(PhysicalAction action) {
        String goalString = action.getGoal();
        if(goalString.isEmpty()){
            goalString = "N/A";
        }
        if (action.getKey() != 0) {
            return "<h1>" + action.getTitle() + "</h1>" +
                    "<b>Description: </b>" + action.getDescription() + "<br />" +
                    "<b>Goal: </b>" + goalString + "<br />";
        } else {
            return "<h1>" + action.getTitle() + "</h1>" +
                    "<b>" + action.getDescription() + "</b>";
        }
    }

    public void createAllActivities() {
        actionList.add(new PhysicalAction(1, "Walking (Inside)", "Use that treadmill!", "3 miles"));
        actionList.add(new PhysicalAction(2, "Walking (Outside)", "Walk those paths!", "3 miles"));
        actionList.add(new PhysicalAction(3, "Biking (Inside)", "Use that bike in the gym!", "5 miles"));
        actionList.add(new PhysicalAction(4, "Biking (Outside)", "Bike along those paths!", "5 miles"));
        actionList.add(new PhysicalAction(5, "Swimming", "Go for a dip!", "5 laps"));
        actionList.add(new PhysicalAction(6, "Strength and Aerobic Classes", "Yoga, dance, etc!", "30 minutes"));
        actionList.add(new PhysicalAction(7, "Golfing", "Just keep swinging!", "Play for an hour"));
        actionList.add(new PhysicalAction(8, "Gardening and Yardwork", "Beautify your landscape!", ""));
        actionList.add(new PhysicalAction(9, "Jogging", "Get that heart rate going!", "2 miles"));

        actionList.add(new PhysicalAction(0, "Add a new activity!", "Press this button to add a new activity to your routine.", ""));
    }

    public void setActivityProgressFragment(ActivityProgressFragment progressFragment) {
        this.progressFragment = progressFragment;
    }
}
