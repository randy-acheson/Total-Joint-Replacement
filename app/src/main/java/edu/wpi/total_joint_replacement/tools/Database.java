package edu.wpi.total_joint_replacement.tools;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Randy on 1/23/2016.
 */
public class Database {
    public ArrayList<PainEntry> painEntries = new ArrayList<>();
    public ArrayList<ActivityEntry> activityEntries = new ArrayList<>();
    Context context = null;

    static final long ONE_MINUTE_IN_MILLIS=60000;

    Calendar date = Calendar.getInstance();
    long t = date.getTimeInMillis();
    Date dateTenMins = new Date(t - (10 * ONE_MINUTE_IN_MILLIS));

    private static Database instance = null;

    protected Database() {
        activityEntries.add(new ActivityEntry(2, 20, dateTenMins));
        activityEntries.add(new ActivityEntry(1, 10, new Date()));
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void readActivityDummyData() throws IOException {
        //activityEntries.add(new ActivityEntry(1, 10, new Date()));
    }

    public void readDummyData() throws IOException {
        DateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss yyyy", Locale.ENGLISH);
        InputStreamReader is = new InputStreamReader(context.getAssets()
                .open("user1.csv"));

        BufferedReader reader = new BufferedReader(is);
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] painElements = line.split(",");
            Date date;
            try {
                date = format.parse(painElements[2]);

                painEntries.add(new PainEntry(
                        Integer.parseInt(painElements[0]),
                        Integer.parseInt(painElements[1]),
                        Joint.valueOf(painElements[3]),
                        date));
            }

            catch (ParseException e) {
                Log.d("Exception", e.toString());
            }
        }
    }

    public ArrayList<PainEntry> getAveragedValues(TimeValue averageSetting, Joint joint, int subjectId){
        ArrayList<PainEntry> painValues = new ArrayList<>();
        Map<String, List<PainEntry>> values = new HashMap<>();
        for(PainEntry entry: painEntries) {

            if(entry.joint != joint || entry.subjectID != subjectId){
                continue;
            }

            String index = "";
            Calendar cal = Calendar.getInstance();
            cal.setTime(entry.time);
            switch (averageSetting) {
                case DAY:
                    index = cal.get(Calendar.YEAR) + " - " + cal.get(Calendar.MONTH) + " - " + cal.get(Calendar.DAY_OF_MONTH);
                    break;
                case WEEK:
                    index = cal.get(Calendar.YEAR) + " - " + cal.get(Calendar.MONTH) + " - " + cal.get(Calendar.WEEK_OF_MONTH);
                    break;
                case MONTH:
                    index = cal.get(Calendar.YEAR) + " - " + cal.get(Calendar.MONTH);
                    break;
                case YEAR:
                    index = cal.get(Calendar.YEAR) + "";
                    break;
            }
            if(!values.containsKey(index)){
                List<PainEntry> newList = new ArrayList<>();
                newList.add(entry);
                values.put(index, newList);
            }
            else{
                values.get(index).add(entry);
            }
        }

        for(String key: values.keySet()){
            float sum = 0;
            float count = 0;
            for(PainEntry entry: values.get(key)){
                sum += entry.painLevel;
                count += 1.0;
            }
            int averagePain = Math.round(sum / count);
            Date date = values.get(key).get(0).time;
            PainEntry newEntry = new PainEntry(subjectId, averagePain, joint, date);
            painValues.add(newEntry);
        }

        Collections.sort(painValues);
        return painValues;
    }

    public enum TimeValue {
        DAY, WEEK, MONTH, YEAR
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
