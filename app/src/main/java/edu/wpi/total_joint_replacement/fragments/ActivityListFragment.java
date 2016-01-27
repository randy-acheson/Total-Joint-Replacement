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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.activities.RecordActivityActivity;
import edu.wpi.total_joint_replacement.entities.PhysicalAction;
import edu.wpi.total_joint_replacement.tools.TextListAdapter;


public class ActivityListFragment extends BaseFragment {

    public ActivityListFragment() {
        title = "Record Activity";
    }
    private RecordActivityActivity recordActivity;

    Boolean b;

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

        // Defined Array values to show in ListView
        final List<PhysicalAction> actionList = PhysicalAction.getAllActivities();
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
            if(recordActivity != null) {
                enterActivityValue(actionList.get(itemPosition));
                //recordActivity.getPager().setCurrentItem(itemPosition + 1);
            }
            }

        });

        return view;
    }

    public void enterActivityValue(PhysicalAction action) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        final AlertDialog dialog;
        alert.setTitle("Pain Survey 1");

        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_activity_record, null);

        TextView title = (TextView) v.findViewById(R.id.activity_title);
        TextView desc = (TextView) v.findViewById(R.id.activity_desc);
        TextView goal = (TextView) v.findViewById(R.id.activity_goal);

        if(action != null){
            title.setText(action.getTitle());
            desc.setText("Description: " + action.getDescription());
            if(!action.getGoal().isEmpty()) {
                goal.setText("Goal: " + action.getGoal());
            }
            else{
                goal.setText("Click here to set a goal!");
            }
        }

        alert.setView(v);
        alert.setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
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
        if (action != PhysicalAction.newAction) {
            return "<h1>" + action.getTitle() + "</h1>" +
                    "<b>Description: </b>" + action.getDescription() + "<br />" +
                    "<b>Goal: </b>" + goalString + "<br />";
        } else {
            return "<h1>" + action.getTitle() + "</h1>" +
                    "<b>" + action.getDescription() + "</b>";
        }
    }
}
