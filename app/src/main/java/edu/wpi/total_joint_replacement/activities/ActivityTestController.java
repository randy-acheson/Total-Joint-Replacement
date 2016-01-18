package edu.wpi.total_joint_replacement.activities;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Joshua on 12/21/2015.
 */
public class ActivityTestController {

    private Map<PageTypes, PageTypeGetter> pages = new HashMap<>();

    private Class[] recordPainPages = { RecordPainActivity.class, RecordPainActivityTabbed.class };
    private Class[] recordActivityPages = { RecordActivityActivity.class };
    private Class[] medicationPages = { PainManagementActivity.class };
    private Class[] viewProgressPages = { ViewProgressActivity.class };
    private Class[] viewMoreOptionsPages = { MoreActivitiesActivity.class };

    private static ActivityTestController instance = new ActivityTestController();

    public enum PageTypes {
        RECORD_PAIN, RECORD_ACTIVITY, PAIN_MANAGEMENT, VIEW_PROGRESS, MORE_OPTIONS
    }

    private class PageTypeGetter{
        ArrayList<Class> classes;
        ArrayList<Class> classesCopy;
        public PageTypeGetter(Class[] classesForType){
            this.classes = new ArrayList<>(Arrays.asList(classesForType));
            this.classesCopy = new ArrayList<>(Arrays.asList(classesForType));
        }

        public Class getNextPageOrFirst(){
            Class theClass = null;
            if(classesCopy.size() != 0){
                int size = classesCopy.size();
                Random rn = new Random();
                int value = rn.nextInt(size);
                theClass = classesCopy.get(value);
                classesCopy.remove(value);
            }
            else if(classes.size() != 0){
                classesCopy.addAll(classes);
                int size = classesCopy.size();
                Random rn = new Random();
                int value = rn.nextInt(size);
                theClass = classesCopy.get(value);
                classesCopy.remove(value);
            }
            return theClass;
        }
    }

    private ActivityTestController(){
        pages.put(PageTypes.RECORD_PAIN, new PageTypeGetter(recordPainPages));
        pages.put(PageTypes.RECORD_ACTIVITY, new PageTypeGetter(recordActivityPages));
        pages.put(PageTypes.PAIN_MANAGEMENT, new PageTypeGetter(medicationPages));
        pages.put(PageTypes.VIEW_PROGRESS, new PageTypeGetter(viewProgressPages));
        pages.put(PageTypes.MORE_OPTIONS, new PageTypeGetter(viewMoreOptionsPages));
    }

    public static void openPage(PageTypes type, Activity activity){
        PageTypeGetter thePageTypeGetter = instance.pages.get(type);
        Class theClass = thePageTypeGetter.getNextPageOrFirst();
        if(theClass != null){
            Intent newIntent = new Intent(activity, theClass);
            activity.startActivity(newIntent);
            activity.finish();
        }
    }
}
