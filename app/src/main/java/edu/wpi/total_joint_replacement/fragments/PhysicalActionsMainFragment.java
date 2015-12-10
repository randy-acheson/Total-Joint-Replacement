package edu.wpi.total_joint_replacement.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.entities.PhysicalActions;


public class PhysicalActionsMainFragment extends BaseFragment {

    public PhysicalActionsMainFragment() {
        title = "Record Activity";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_record_main, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.activitiesList);

        // Defined Array values to show in ListView
        List<PhysicalActions> actionList = PhysicalActions.getAllActivities();
        ArrayList<TextView> views = new ArrayList<>();
        for (int i = 0; i < actionList.size(); i++) {
            TextView newView = new TextView(view.getContext());
            newView.setText(getActivityHTML(actionList.get(i)));
            views.add(newView);
        }

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ActivitiesAdapter adapter = new ActivitiesAdapter(view.getContext(), android.R.layout.simple_list_item_1, views);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);


            }

        });

        return view;
    }

    private class ActivitiesAdapter extends ArrayAdapter<TextView> {

        private ArrayList<TextView> items;

        public ActivitiesAdapter(Context context, int layoutId, ArrayList<TextView> items) {
            super(context, layoutId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            View mView = v;


            //if(mView == null){
            LayoutInflater vi = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(R.layout.row_text, null);
            // }

            TextView text = (TextView) mView.findViewById(R.id.textView);

            if (items.get(position) != null) {
                text.setTextColor(Color.BLACK);
                text.setTextSize(10);
                text.setText(Html.fromHtml(items.get(position).getText() + ""));
                text.setTextIsSelectable(false);

            }


            return mView;
        }
    }

    private String getActivityHTML(PhysicalActions action) {

        return  "<h1>" + action.getTitle() + "</h1>" +
                "<b>Description: </b>" + action.getDescription() + "<br />" +
                "<b>Stress Level: </b>" + action.getGoal() + "<br />";
    }

}
