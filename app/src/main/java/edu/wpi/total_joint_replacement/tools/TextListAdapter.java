package edu.wpi.total_joint_replacement.tools;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.wpi.total_joint_replacement.R;

/**
 * Created by Joshua on 1/18/2016.
 */
public class TextListAdapter extends ArrayAdapter<TextView> {
    private ArrayList<TextView> items;
    private Context context;

    public TextListAdapter(Context context, int layoutId, ArrayList<TextView> items) {
        super(context, layoutId, items);
        this.items = items;
        this.context = context;
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
