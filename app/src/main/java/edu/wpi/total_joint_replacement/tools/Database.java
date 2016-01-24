package edu.wpi.total_joint_replacement.tools;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by Randy on 1/23/2016.
 */
public class Database {
    public ArrayList<PainEntry> painEntries = new ArrayList<>();
    Context context = null;

    private static Database instance = null;

    protected Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
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

    public void setContext(Context context) {
        this.context = context;
    }

}
