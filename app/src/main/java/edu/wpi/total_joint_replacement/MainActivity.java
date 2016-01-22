package edu.wpi.total_joint_replacement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.wpi.total_joint_replacement.activities.ActivityTestController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openRecordPain(View view) {
        ActivityTestController.openPage(ActivityTestController.PageTypes.RECORD_PAIN, this);
        /*Intent newPage = new Intent(getApplicationContext(), RecordPainActivity.class);
        startActivity(newPage);
        finish();*/
    }

    public void openRecordActivity(View view) {
        ActivityTestController.openPage(ActivityTestController.PageTypes.RECORD_ACTIVITY, this);
        /*Intent newPage = new Intent(getApplicationContext(), RecordActivityActivity.class);
        startActivity(newPage);
        finish();*/
    }

    public void openPainManagement(View view) {
        ActivityTestController.openPage(ActivityTestController.PageTypes.PAIN_MANAGEMENT, this);
    }

    public void openReport(View view) {
        ActivityTestController.openPage(ActivityTestController.PageTypes.VIEW_PROGRESS, this);
    }

    public void openMore(View view){
        ActivityTestController.openPage(ActivityTestController.PageTypes.MORE_OPTIONS, this);
    }

    public void openHome(View view){

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
