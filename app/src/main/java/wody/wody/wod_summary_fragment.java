package wody.wody;

/**
 * Created by Falch on 24/11/15.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import models.TimeExercise;
import models.WOD;

/**
 * Created by dasas on 12/09/15.
 */
public class wod_summary_fragment extends Fragment {
    View rootview;
    List<String> list;
    WOD wod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.wod_summary_fragment, container, false);

        if (wod == null){
            createTempWod();
        }

        ArrayList<TimelineObject> timelineList = new ArrayList<>();

        for(TimeExercise w:wod.getTimeExercises()){
            long totalTime = w.getEndTime() - w.getStartTime();
            TimelineObject tObject = new TimelineObject(w.getName(), totalTime);
            timelineList.add(tObject);
        }

        TableLayout tableLayout = (TableLayout) rootview.findViewById(R.id.tableLayoout);
        tableLayout.setPadding(0, 50, 0, 0);

        ImageView bubbleChartView = (ImageView) rootview.findViewById(R.id.imageView);
        bubbleChartView.setImageResource(R.drawable.bubble_chart);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1100, 800);
        bubbleChartView.setLayoutParams(layoutParams);

        //generates timeline
        int counter = 1;
        for(TimelineObject to : timelineList){
            TableRow row = new TableRow(rootview.getContext().getApplicationContext());
            String name = to.getName();
            long time = to.getTime();

            TextView dateTextView = new TextView(rootview.getContext().getApplicationContext());
            dateTextView.setTextColor(Color.BLACK);
            dateTextView.setGravity(Gravity.RIGHT);
            dateTextView.setWidth(300);
            dateTextView.setTextSize(20);
            row.addView(dateTextView);

            ImageView circleView = new ImageView(rootview.getContext().getApplicationContext());
            circleView.setImageResource(R.drawable.green_circle);
            circleView.setPadding(40,0,40,0);
            row.addView(circleView);

            LinearLayout ll = new LinearLayout(rootview.getContext().getApplicationContext());
            ll.setOrientation(LinearLayout.VERTICAL);
            TextView nameTextView = new TextView(rootview.getContext().getApplicationContext());
            nameTextView.setTextColor(Color.BLACK);
            nameTextView.setText(name);
            nameTextView.setWidth(300);
            nameTextView.setTextSize(20);
            TextView timeTextView = new TextView(rootview.getContext().getApplicationContext());
            timeTextView.setTypeface(null, Typeface.ITALIC);
            timeTextView.setText(time+"min");
            timeTextView.setWidth(300);
            timeTextView.setTextSize(15);
            timeTextView.setTextColor(Color.BLACK);
            ll.addView(nameTextView);
            ll.addView(timeTextView);
            row.addView(ll);

            row.setGravity(Gravity.CENTER_HORIZONTAL);
            tableLayout.addView(row);

            if(counter < timelineList.size()){
                ImageView i = new ImageView(rootview.getContext());
                i.setImageResource(R.drawable.grey_line);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(200, 200);
                i.setLayoutParams(layoutParams1);
                tableLayout.addView(i);
            }

            counter++;
        }


        return rootview;

    }

    private void createTempWod() {
        wod = new WOD("WODy","test",3);
        wod.setsDate("Today");
        long time = 0;

        for (int i=0;i<6;i++){
            TimeExercise timeExercise = new TimeExercise("WODy"+i,10);
            timeExercise.setStartTime(0);
            long timeTemp = (long) (1 +i);
            time += timeTemp;
            timeExercise.setEndTime(timeTemp);
            wod.addTimeExercise(timeExercise);
        }
        wod.setStartTime(time);
    }
}
