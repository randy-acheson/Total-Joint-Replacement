package edu.wpi.total_joint_replacement.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

import edu.wpi.total_joint_replacement.MainActivity;
import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.fragments.PainValueFragment;
import edu.wpi.total_joint_replacement.fragments.StiffnessValueFragment;
import edu.wpi.total_joint_replacement.tools.*;

public class RecordPainActivity extends AppCompatActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private PageController mPager;

    private Button nextButton;
    private Button previousButton;

    private PainValueFragment painFragment = new PainValueFragment();
    private StiffnessValueFragment stiffnessFragment = new StiffnessValueFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_record_pain);



        nextButton = (Button) findViewById(R.id.statusNextButton);
        previousButton = (Button) findViewById(R.id.statusPreviousButton);
        ArrayList<android.support.v4.app.Fragment> pages = new ArrayList<>();

        pages.add(painFragment);
        pages.add(stiffnessFragment);


        //Create the page updater, and give it the objects it needs to manipulate.
        mPager = (PageController) findViewById(R.id.statusUpdatePager);
        mPager.registerPageController(this, nextButton, previousButton, pages);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            MainActivity.returnToMainActivity(true, this);
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.status_exit){
            MainActivity.returnToMainActivity(true, this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void nextButtonClick(View v) {
        if(mPager.isOnLastPage()) {
            submit();
        }
        else{
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        }
    }

    public void previousButtonClick(View v){
        mPager.setCurrentItem(mPager.getCurrentItem() - 1);
    }

    public void submit(){

        MainActivity.returnToMainActivity(false, this);
    }
}
