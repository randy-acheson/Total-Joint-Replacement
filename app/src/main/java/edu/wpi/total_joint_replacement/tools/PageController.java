package edu.wpi.total_joint_replacement.tools;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class is used to control swapping through fragments on an activity.
 */
public class PageController extends ViewPager {
    private Button nextButton;
    private Button previousButton;
    private ArrayList<Fragment> pages = null;
    private boolean isChangesDisabled = false;
    private Map<Fragment, String> titles = null;
    private TextView titleTextBox = null;
    private FragmentActivity fActivity;

    /**
     * Extends the ViewPager constructor to create this custom pager.
     * @param context
     * @param attrs
     */
    public PageController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Registers the elements necessary for the controller to work.
     * Note: These are not taken in the constructor, because the constructor is called when parsing the XML,
     * so these need to be registered programmatically.
     * @param activity the activity page using the fragment viewer.
     * @param nextButton the button used for going to the next page.
     * @param previousButton the button used for  going to the previous page.
     * @param pages the pages to display. They will be displayed in the order of the array list.
     */
    public void registerPageController(FragmentActivity activity, Button nextButton, Button previousButton, ArrayList<Fragment> pages) {

        registerPageController(activity, nextButton, previousButton, pages, false);
    }

    public void registerPageController(FragmentActivity activity, Button nextButton, Button previousButton, ArrayList<Fragment> pages, boolean disableButtonChanges) {

        if(nextButton != null) {
            this.nextButton = nextButton;
        }
        else{
            this.nextButton = new Button(activity);
        }

        if(previousButton != null) {
            this.previousButton = previousButton;
        }
        else{
            this.previousButton = new Button(activity);
        }
        this.pages = pages;
        this.isChangesDisabled = disableButtonChanges;
        this.fActivity = activity;

        ScreenSlidePagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(activity.getSupportFragmentManager());
        setAdapter(mPagerAdapter);
        handleNavigationButtons();
    }

    public void registerPageTitleUpdater(Map<Fragment, String> titles, TextView textBox){
        this.titles = titles;
        this.titleTextBox = textBox;
    }

    /**
     * A simple pager adapter that represents the variable pages.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            handleNavigationButtons();

            if(titles != null){
                Fragment current = pages.get(getCurrentItem());
                if(titles.containsKey(current)){
                    titleTextBox.setText(titles.get(current));
                }
            }

            //hideKeyboard(fActivity);

            //container.requestFocus();
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if(view == null) {
            view = new View(activity);
        }
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    /**
     * This function handles enabling and disabling the navigation buttons depending upon the current
     * page.
     */
    private void handleNavigationButtons() {
        int position = getCurrentItem();
        if(!isChangesDisabled) {
            if (position == 0) {
                previousButton.setEnabled(false);
            } else {
                previousButton.setEnabled(true);
            }
        } else {
            if (position == 0) {
                previousButton.setEnabled(false);
                previousButton.setVisibility(View.INVISIBLE);
            } else {
                previousButton.setEnabled(true);
                previousButton.setVisibility(View.VISIBLE);
            }
        }

        if(!isChangesDisabled) {
            if (position == pages.size() - 1) {
                nextButton.setEnabled(true);
                nextButton.setText(Html.fromHtml("<b>Submit</b>"));
            } else {
                nextButton.setEnabled(true);
                nextButton.setText("Next");
            }
        }
        else{
            if (position == pages.size() - 1) {
                nextButton.setEnabled(false);
                nextButton.setVisibility(View.INVISIBLE);
            } else {
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
            }
        }

        if(pages.size() <= 1){
            //nextButton.setVisibility(View.INVISIBLE);
            previousButton.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isOnLastPage(){
        int position = getCurrentItem();
        return position == pages.size() - 1;
    }
}
