package wody.wody;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
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
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);
        final TextView textView = (TextView) rootview.findViewById(R.id.screen);
        Context context = getActivity().getApplicationContext();

        final ExercisePrediction ep = new ExercisePrediction(context, "model_8000.model");

        //print result from arff file
        ep.getPrediction(context, "wodData.arff");

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

                    System.out.println("The predicted value of instance: "+prediction);
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

        return rootview;

    }
}
