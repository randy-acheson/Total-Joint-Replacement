package edu.wpi.total_joint_replacement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openRecordPain(View view) {
        /*Intent newPage = new Intent(getApplicationContext(), PatientInfoActivity.class);
        startActivity(newPage);
        finish(); */
    }

    public void openRecordActivity(View view) {
    }

    public void openPainManagement(View view) {
    }

    public void openReport(View view) {
    }

}
