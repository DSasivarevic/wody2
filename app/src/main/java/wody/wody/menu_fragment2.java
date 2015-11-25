package wody.wody;


import android.content.Context;
import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;

import controllers.Globals;
import controllers.SensorsService;
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
    private RadioGroup radioGroup;
    private final RadioButton[] radioBtns = new RadioButton[4];
    private Intent mServiceIntent;
    private File mFeatureFile;

    private Button btnDelete;

    private WOD wod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu2_layout, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final RadioGroup radioExerciseGroup = (RadioGroup) rootview.findViewById(R.id.Exercise);
        String wodName = "";

        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        acSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, acSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mServiceIntent = new Intent(getActivity(), SensorsService.class);


        final Button btn = (Button) rootview.findViewById(R.id.btnStart);
        final Button btn2 = (Button) rootview.findViewById(R.id.button2);
        btn2.setVisibility(View.GONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioExerciseGroup.getCheckedRadioButtonId();
                RadioButton checkedRadioButton = (RadioButton) rootview.findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();
                wod.start();
                btn.setBackgroundColor(Color.RED);
                wod.setName(((TextView) rootview.findViewById(R.id.wodName)).getText().toString());
                if (btn.getText().equals("Start")) {
                    Log.e("TESTING","****************************************************************************\n" +
                                    "****************************************************************************\n" +
                                    "****************************************************************************\n");


                    wod.addExercise(new TimeExercise(text, 10));
                    TimeExercise ex = (TimeExercise) wod.getExercises().get(wod.getCurrentExercise());
                    ex.start();
                    btn.setText(ex.getRepetitions() + " " + ex.getName());
                } else {

                    int next = wod.nextExercise();
                   Log.e("ERROR", "" + next);
                    if (next < wod.getExercises().size()) {
                        Log.e("ERROR", "" + wod.getExercises().size());
                        TimeExercise old_ex = (TimeExercise) wod.getExercises().get(next);
                        old_ex.stop();
                        TimeExercise ex = (TimeExercise) wod.getExercises().get(next);
                        btn.setText(ex.getRepetitions() + " " + ex.getName());

                        Bundle extras = new Bundle();
                        extras.putString(Globals.CLASS_LABEL_KEY, ex.getName());
                        mServiceIntent.putExtras(extras);
                        //getContext().startService(mServiceIntent);
                        Log.e("ERROR", "StartActivity" + wod.getExercises().size());


                        ex.start();
                    } else {
                        TimeExercise old_ex = (TimeExercise) wod.getExercises().get(wod.getExercises().size() - 1);
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
                Toast.makeText(rootview.getContext(), "SAVED TO SERVER", Toast.LENGTH_LONG);
            }
        });

        int checkedId = radioExerciseGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = (RadioButton) rootview.findViewById(checkedId);
        String text = checkedRadioButton.getText().toString();

        wod = new WOD("","",1);
        wod.addExercise(new TimeExercise(text, 10));
        //wod.addExercise(new TimeExercise("Sit-ups", 10));
        //wod.addExercise(new TimeExercise("Air Squat", 10));
        //wod.addExercise(new TimeExercise("Sit-ups", 10));
        return rootview;
    }

    @Override
    public void onSensorChanged(SensorEvent evt) {
        //Log.e("TESTING",evt.values[0]+","+evt.values[1] + ","+ evt.values[2]);

       //x = evt.values[0], y = evt.values[1], z = evt.values[2], timestamp = evt.timestamp
        if(wod.getCurrentExercise() < wod.getExercises().size()){
            TimeExercise ex = (TimeExercise)wod.getExercises().get(wod.getCurrentExercise());
            SensorData s = new SensorData(evt.values, evt.timestamp);
            Log.e("Testing", s.getData().toString());
            ex.addData(s);
        }


        //Log.d(""+Log.VERBOSE,""+evt.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


}
        }