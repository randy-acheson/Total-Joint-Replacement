package edu.wpi.total_joint_replacement.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.total_joint_replacement.MainActivity;
import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.entities.PhysicalAction;
import edu.wpi.total_joint_replacement.fragments.PhysicalActionsFragment;
import edu.wpi.total_joint_replacement.fragments.PhysicalActionsMainFragment;
import edu.wpi.total_joint_replacement.fragments.PhysicalActionsNewFragment;
import edu.wpi.total_joint_replacement.tools.PageController;
import edu.wpi.total_joint_replacement.tools.TextListAdapter;

public class MoreActivitiesActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_more_activities);

        final ListView listView = (ListView) this.findViewById(R.id.moreActivitiesList);


        // Defined Array values to show in ListView
        final List<String> activityList = new ArrayList<>();
        activityList.add("<h1>Profile</h1> Update your personal details and choices.");
        activityList.add("<h1>Set Goals</h1> Set your goals for all of your activities.");
        activityList.add("<h1>Research Findings</h1> Read articles concerning Arthritis.");
        activityList.add("<h1>FAQ (Frequently Asked Questions)</h1> Read questions that are frequently asked.");
        ArrayList<TextView> views = new ArrayList<>();
        for (int i = 0; i < activityList.size(); i++) {
            TextView newView = new TextView(this);
            newView.setText(activityList.get(i));
            views.add(newView);
        }

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        TextListAdapter adapter = new TextListAdapter(this, android.R.layout.simple_list_item_1, views);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {



            }

        });


    }

}
