package edu.wpi.total_joint_replacement.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.wpi.total_joint_replacement.R;

//import android.widget.NumberPicker;


public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected String title = "";

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            if(context != null && context instanceof Activity){
                ((Activity) context).setTitle(title);
            }
        }
    }


}
