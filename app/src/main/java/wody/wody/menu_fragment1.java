package wody.wody;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import models.ExercisePrediction;

/**
 * Created by dasas on 12/09/15.
 */
public class menu_fragment1 extends Fragment {
    View rootview;
    List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.main2, container, false);

        final RoundButton btnStartStop = (RoundButton) rootview.findViewById(R.id.btnStartStop);

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            int click = 0;
            @Override
            public void onClick(View v) {
                btnStartStop.setRoundColor(0xFFF0F0FF);
                btnStartStop.refreshDrawableState();

                if(click %2 == 0){
                    //Change to red color
                    btnStartStop.setText("Stop");
                    btnStartStop.setRoundColor(0xFFFF0000);
                }
                else if(click % 2 == 1){
                    //Change to green color
                    btnStartStop.setText("Start");
                    btnStartStop.setRoundColor(0xFF64DD17);
                    //TODO: End Wod and end summary
                }
                btnStartStop.invalidate();
                click++;
            }
        });


        /*
        final TextView textView = (TextView) rootview.findViewById(R.id.screen);
        Context context = getActivity().getApplicationContext();

        final ExercisePrediction ep = new ExercisePrediction(context, "model_40000.model");

        //print result from arff file
        ep.getPrediction(context, "KevinTestData.arff", 5);

        //get prediction from single instance
        final Button button = (Button) rootview.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText xView = (EditText) rootview.findViewById(R.id.valuex);
                EditText yView = (EditText) rootview.findViewById(R.id.valuey);
                EditText zView = (EditText) rootview.findViewById(R.id.valuez);

                try {
                    double xvalue = Double.parseDouble(xView.getText().toString());
                    double yvalue = Double.parseDouble(yView.getText().toString());
                    double zvalue = Double.parseDouble(zView.getText().toString());

                    String prediction = ep.getPrediction(xvalue, zvalue, yvalue);

//                    System.out.println("The predicted value of instance: "+prediction);
                    textView.setText(prediction);

                }catch (NumberFormatException e){
                    e.printStackTrace();
                    TextView textView = (TextView) rootview.findViewById(R.id.screen);
                    textView.setText("Kun tal!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        */

        return rootview;

    }
}
