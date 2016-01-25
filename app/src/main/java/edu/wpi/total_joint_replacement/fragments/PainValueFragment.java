package edu.wpi.total_joint_replacement.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.total_joint_replacement.R;



public class PainValueFragment extends BaseFragment {

    public PainValueFragment() {
        title = "Record Pain";
    }

    private Button B1_ext;
    private Button B2_ext;
    private Button B3_ext;
    private Button B4_ext;
    private Button B5_ext;
    private LinearLayout LL1;
    private LinearLayout LL2;
    private LinearLayout LL3;
    private LinearLayout LL4;
    private LinearLayout LL5;
    private LinearLayout LL6;
    private int currentSelectedFace = -1;

    boolean b = false;
    Drawable d1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pain_record_2, container, false);

        B1_ext = (Button) view.findViewById(R.id.bodybtn1_ext);
        B2_ext = (Button) view.findViewById(R.id.bodybtn2_ext);
        B3_ext = (Button) view.findViewById(R.id.bodybtn3_ext);
        B4_ext = (Button) view.findViewById(R.id.bodybtn4_ext);
        B5_ext = (Button) view.findViewById(R.id.bodybtn5_ext);

        d1= getResources().getDrawable(R.drawable.round_button_with_center);
        return view;

    }

    public void buttonSelected(View view) {


        int id = view.getId();
        switch (id) {
            case R.id.bodybtn1_ext:
                horizontaltap(B1_ext);
                break;
            case R.id.bodybtn2_ext:
                horizontaltap(B2_ext);
                break;
            case R.id.bodybtn3_ext:
                horizontaltap(B3_ext);
                break;
            case R.id.bodybtn4_ext:
                horizontaltap(B4_ext);
                break;
            case R.id.bodybtn5_ext:
                horizontaltap(B5_ext);
                break;



        }
    }


    public void horizontaltap(final Button button) {
        d1 = ContextCompat.getDrawable(getContext(), R.drawable.round_button_with_center);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        final AlertDialog dialog;
        alert.setTitle("Pain Survey 1");

        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = inflater.inflate(R.layout.popup1, null);
        View v = inflater.inflate(R.layout.transparent_buttons_horizontal, null);

        LL1 = (LinearLayout) v.findViewById(R.id.layoutbutton1);
        LL2 = (LinearLayout) v.findViewById(R.id.layoutbutton2);
        LL3 = (LinearLayout) v.findViewById(R.id.layoutbutton3);
        LL4 = (LinearLayout) v.findViewById(R.id.layoutbutton4);
        LL5 = (LinearLayout) v.findViewById(R.id.layoutbutton5);
        LL6 = (LinearLayout) v.findViewById(R.id.layoutbutton6);

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

        b = false;

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int colorToChangeTo = 0;
                b = true;
                switch (currentSelectedFace) {
                    case 6:
                        colorToChangeTo = getColorForLevel(1);
                        break;
                    case 5:
                        colorToChangeTo = getColorForLevel(2);
                        break;
                    case 4:
                        colorToChangeTo = getColorForLevel(3);
                        break;
                    case 3:
                        colorToChangeTo = getColorForLevel(4);
                        break;
                    case 2:
                        colorToChangeTo = getColorForLevel(5);
                        break;
                    case 1:
                        colorToChangeTo = getColorForLevel(6);
                        break;
                    default:
                        b = false;
                        Toast.makeText(getContext(), "Please Select A Pain Level",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

                if (b) {
                    d1.setColorFilter(colorToChangeTo, PorterDuff.Mode.OVERLAY);
                    button.setBackgroundDrawable(d1);
                    //System.out.println(d1);
                    // button_ext.setBackground(d1);
                    dialog.dismiss();

                }

                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });


    }

    public void enableUnclickedLayouts(int selected) {
        currentSelectedFace = selected;
        LL1.setEnabled(selected != 1);
        LL2.setEnabled(selected != 2);
        LL3.setEnabled(selected != 3);
        LL4.setEnabled(selected != 4);
        LL5.setEnabled(selected != 5);
        LL6.setEnabled(selected != 6);
    }

    public static int getColorForLevel(int level){
        switch(level){
            default:
            case 0:
                return Color.GRAY;
            case 1:
                return Color.parseColor("#FF669900");
            case 2:
                return Color.parseColor("#FF99CC00");
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.parseColor("#FFFFBB33");
            case 5:
                return Color.parseColor("#FFFF8800");
            case 6:
                return Color.RED;

        }
    }
}
