package edu.wpi.total_joint_replacement.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.wpi.total_joint_replacement.MainActivity;
import edu.wpi.total_joint_replacement.R;

/**
 * Created by Joshua on 12/21/2015.
 */
public class BaseActivity extends AppCompatActivity {

    protected boolean requiresConfirmationForExit = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        View view = menu.findItem(R.id.status_exit).getActionView();
        final Activity thisActivity = this;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.returnToMainActivity(requiresConfirmationForExit, thisActivity);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.status_exit){
            MainActivity.returnToMainActivity(requiresConfirmationForExit, this);
        }

        return super.onOptionsItemSelected(item);
    }
}
