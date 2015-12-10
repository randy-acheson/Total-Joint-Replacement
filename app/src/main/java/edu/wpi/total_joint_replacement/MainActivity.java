package edu.wpi.total_joint_replacement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.wpi.total_joint_replacement.activities.RecordActivityActivity;
import edu.wpi.total_joint_replacement.activities.RecordPainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openRecordPain(View view) {
        Intent newPage = new Intent(getApplicationContext(), RecordPainActivity.class);
        startActivity(newPage);
        finish();
    }

    public void openRecordActivity(View view) {
        Intent newPage = new Intent(getApplicationContext(), RecordActivityActivity.class);
        startActivity(newPage);
        finish();
    }

    public void openPainManagement(View view) {
    }

    public void openReport(View view) {
    }

    public static void returnToMainActivity(boolean askForConfirmation, final Activity activity){
        if(!askForConfirmation) {
            Intent mainIntent = new Intent(activity, MainActivity.class);
            activity.startActivity(mainIntent);
            activity.finish();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setTitle("Confirm Exit");
            builder.setMessage("Are you sure you want to exit this page? \n\n All data entered will be lost.\n");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    Intent mainIntent = new Intent(activity, MainActivity.class);
                    activity.startActivity(mainIntent);
                    activity.finish();
                }

            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

}
