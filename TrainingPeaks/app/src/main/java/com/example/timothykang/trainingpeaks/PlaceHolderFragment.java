package com.example.timothykang.trainingpeaks;

/**
 * Created by Timothy Kang on 6/3/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceHolderFragment extends Fragment {

    private static JSONArray jsonArray;

    private static JSONObject obje;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceHolderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceHolderFragment newInstance(int sectionNumber, JSONObject obj) {

        //set global obje
        obje = obj;

        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //extract array information dependent on the heart rate or speeds
        try {
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1)
                jsonArray = obje.getJSONObject("workoutDetailData").getJSONArray("peakHeartRates");
            else
                jsonArray = obje.getJSONObject("workoutDetailData").getJSONArray("peakSpeeds");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new SimpleRVAdapter(jsonArray));
        return rv;
    }

    /**
     * A Simple Adapter for the RecyclerView
     */
    public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

        List<JSONObject> jsonValues = new ArrayList<JSONObject>();

        public SimpleRVAdapter(JSONArray object) {

            boolean exists = false;
            //create arraylist to sort the json values
            jsonValues = new ArrayList<JSONObject>();
            Set<String> checkDuplicate = new HashSet<String>();
            for (int i = 0; i < object.length(); i++) {
                try {
                    if(!checkDuplicate.contains(object.getJSONObject(i).toString())) {
                        checkDuplicate.add(object.getJSONObject(i).toString());
                        jsonValues.add(object.getJSONObject(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                //sort dataSource
                Collections.sort(jsonValues, new Comparator<JSONObject>() {
                    //keys values to sort by
                    private static final String KEY_NAME1 = "value";
                    private static final String KEY_NAME2 = "interval";

                    int check;

                    @Override
                    public int compare(JSONObject a, JSONObject b) {
                        int intA = 0;
                        int intB = 0;

                        int valA = 0;
                        int valB = 0;

                        //extract info to sort by
                        try {
                            valA = (int) a.get(KEY_NAME1);
                            valB = (int) b.get(KEY_NAME1);

                            intA = (int) a.get(KEY_NAME2);
                            intB = (int) b.get(KEY_NAME2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //check on value
                        check = -Double.compare(valA, valB);
                        //if values the same check on interval
                        if (check == 0) {
                            return -Integer.compare(intA, intB);
                        }
                        return check;

                    }
                });

            } else {
                //sort dataSource
                Collections.sort(jsonValues, new Comparator<JSONObject>() {
                    //keys values to sort by
                    private static final String KEY_NAME1 = "value";
                    private static final String KEY_NAME2 = "interval";

                    int check;

                    @Override
                    public int compare(JSONObject a, JSONObject b) {
                        int intA = 0;
                        int intB = 0;

                        double valA = 0;
                        double valB = 0;

                        //extract the data
                        try {
                            valA = (double) a.get(KEY_NAME1);
                            valB = (double) b.get(KEY_NAME1);

                            intA = (int) a.get(KEY_NAME2);
                            intB = (int) b.get(KEY_NAME2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //check on value
                        check = Double.compare(valA, valB);
                        //check on interval if the values are the same
                        if (check == 0) {
                            return Integer.compare(intA, intB);
                        }
                        return check;
                    }
                });
            }

        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new TextView(parent.getContext());
            SimpleViewHolder viewHolder = new SimpleViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {

            double decimalTime;
            int fullMinutes;
            String peak;
            int peaks;
            int peakMinutes;
            double seconds;
            String time;
            DecimalFormat df = new DecimalFormat("00");

            //print on either hearts or speeds
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                try {
                    holder.textView.setText("Peak " + jsonValues.get(position).get("interval").toString()
                            + " sec " + jsonValues.get(position).get("value").toString() + " bpm");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    //get the seconds from interval to set the peak times in the correct format
                    peaks = (int) jsonValues.get(position).get("interval");
                    if(peaks % 60 == 0){

                        peak = peaks/60 + " min ";

                    }
                    else if (peaks / 60 >= 1){

                        peakMinutes = (int) peaks / 60;

                        peak = peakMinutes + ":" + df.format(peaks%60) + " ";
                    }
                    else{

                        peak = jsonValues.get(position).get("interval").toString() + " sec ";

                    }

                    //the following lines are for setting the min/mile in the correct format
                    decimalTime = (double) jsonValues.get(position).get("value");

                    //cast to int to remove past the decimal point
                    fullMinutes = (int) decimalTime;

                    //seperate the percentage of a minute from decimal time
                    seconds = decimalTime - fullMinutes;

                    seconds = seconds * 60;

                    time = fullMinutes + ":" + df.format(seconds);

                    holder.textView.setText("Peak " + peak + time + " min/mi");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getItemCount() {
            return jsonValues.size();
        }
    }

    /**
     * A Simple ViewHolder for the RecyclerView
     */
    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
