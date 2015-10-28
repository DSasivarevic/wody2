package wody.wody;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;

/**
 * Created by dasas on 12/09/15.
 */
public class menu_fragment3 extends Fragment {
    View rootview;
    //private WekaTest ws;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu3_layout, container, false);

        try {
           // ws.test();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Classifier cModel = (Classifier)new NaiveBayes();
        //cModel.buildClassifier();

        try {
            //weka.core.SerializationHelper.write("/assets/nBayes.model", cModel);
        } catch (Exception e) {
            e.printStackTrace();
        };

        Classifier cls = null;
        try {
            cls = (Classifier) weka.core.SerializationHelper.read("test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

// Test the model
        //Evaluation eTest = new Evaluation();
        //eTest.evaluateModel(cls, isTrainingSet);
        return rootview;
    }
}
