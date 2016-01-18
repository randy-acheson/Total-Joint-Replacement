package edu.wpi.total_joint_replacement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.total_joint_replacement.MainActivity;
import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.entities.PhysicalAction;
import edu.wpi.total_joint_replacement.fragments.PhysicalActionsFragment;
import edu.wpi.total_joint_replacement.fragments.PhysicalActionsMainFragment;
import edu.wpi.total_joint_replacement.fragments.PhysicalActionsNewFragment;
import edu.wpi.total_joint_replacement.tools.PageController;

public class RecordActivityActivity extends BaseActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private PageController mPager;

    private Button nextButton;
    private Button previousButton;

    private PhysicalActionsMainFragment mainFragment = new PhysicalActionsMainFragment();
    private PhysicalActionsNewFragment newActivity= new PhysicalActionsNewFragment();

    public PageController getPager(){
        return mPager;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requiresConfirmationForExit = true;


        setContentView(R.layout.activity_record_activity);



        nextButton = new Button(this);
        previousButton = (Button) findViewById(R.id.prevButton);
        ArrayList<android.support.v4.app.Fragment> pages = new ArrayList<>();

        pages.add(mainFragment);
        final List<PhysicalAction> actionList = PhysicalAction.getAllActivities();
        for (int i = 0; i < actionList.size(); i++) {
            if(actionList.get(i) == PhysicalAction.newAction){
                pages.add(newActivity);
            }
            else {
                PhysicalActionsFragment newAction = new PhysicalActionsFragment();
                newAction.setPhysicalAction(actionList.get(i));
                pages.add(newAction);
            }
        }


        //Create the page updater, and give it the objects it needs to manipulate.
        mPager = (PageController) findViewById(R.id.statusUpdatePager);
        mPager.registerPageController(this, nextButton, previousButton, pages, true);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            MainActivity.returnToMainActivity(requiresConfirmationForExit, this);
        } else {
            mPager.setCurrentItem(0);
        }
    }



    public void onNewActivityClick(View view){
        newActivity.saveActivity();
        Intent newIntent = new Intent(this, RecordActivityActivity.class);
        this.startActivity(newIntent);
        this.finish();
    }


    public void previousButtonClick(View v){
        mPager.setCurrentItem(0);
    }
}
