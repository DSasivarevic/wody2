package wody.wody;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import controllers.Server;
import models.SensorData;
import models.TimeExercise;
import models.WOD;

/**
 * Created by dasas on 12/09/15.
 */
public class menu_fragment2 extends Fragment implements SensorEventListener {
    View rootview;

    private SensorManager mSensorManager;
    private Sensor acSensor;

    private WOD wod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu2_layout, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        acSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, acSensor, SensorManager.SENSOR_DELAY_NORMAL);

        final Button btn = (Button) rootview.findViewById(R.id.btnStart);
        final Button btn2 = (Button) rootview.findViewById(R.id.button2);
        btn2.setVisibility(View.GONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wod.start();
                btn.setBackgroundColor(Color.RED);
                if(btn.getText().equals("Start")){
                    TimeExercise ex = (TimeExercise) wod.getExercises().get(wod.getCurrentExercise());
                    ex.start();
                    btn.setText(ex.getRepetitions()+ " " + ex.getName());
                }else{

                    int next = wod.nextExercise();
                    Log.e("ERROR", "" + next);
                    if(next < wod.getExercises().size()) {
                        Log.e("ERROR", ""+wod.getExercises().size());
                        TimeExercise old_ex = (TimeExercise) wod.getExercises().get(next);
                        old_ex.stop();
                        TimeExercise ex = (TimeExercise) wod.getExercises().get(next);
                        btn.setText(ex.getRepetitions() + " " + ex.getName());
                        ex.start();
                    }
                    else{
                        TimeExercise old_ex = (TimeExercise) wod.getExercises().get(wod.getExercises().size()-1);
                        old_ex.stop();
                        wod.stop();
                        btn.setText("DONE");
                        btn2.setVisibility(View.VISIBLE);
                        btn.setBackgroundColor(Color.GRAY);


                    }

                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Server server = new Server();
                Gson gson = new Gson();
                String s = gson.toJson(wod);
                server.saveWOD(s);
            }
        });
        wod = new WOD("Half-cindy","",2);
        wod.addExercise(new TimeExercise("Push-ups", 5));
        wod.addExercise(new TimeExercise("Sit-ups", 10));
        wod.addExercise(new TimeExercise("Push-ups", 5));
        wod.addExercise(new TimeExercise("Sit-ups", 10));
        return rootview;
    }

    @Override
    public void onSensorChanged(SensorEvent evt) {
        //x = evt.values[0], y = evt.values[1], z = evt.values[2], timestamp = evt.timestamp
        TimeExercise ex = (TimeExercise)wod.getExercises().get(0);
        SensorData s = new SensorData(evt.values, evt.timestamp);
        ex.addData(s);
        //Log.d(""+Log.VERBOSE,""+evt.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


}
        }