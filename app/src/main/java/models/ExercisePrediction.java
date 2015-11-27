package models;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controllers.Globals;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * Created by Falch on 07/11/15.
 */
public class ExercisePrediction {
    private Classifier cls = null;
    private Instances instance = null;
    private TextView txtPrediction;
    private BufferedWriter out;
    private ArrayList<Long> timestamps = new ArrayList<Long>();
    String[] splits = {"Jump_up", "Frontal_elevation_of_arms", "Knees_bending_(crouching)"};

    public ExercisePrediction(Context context, String modelName) {

        //ceate classifier
        try {
            InputStream wodModel = context.getAssets().open(modelName);
            cls = (Classifier) weka.core.SerializationHelper.read(wodModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get prediction from arff
    public ArrayList<Long> getPrediction(Context context, String arffName, int k){
        Instances instances = null;
        ArrayList<String> predictionArray = new ArrayList<String>();

        try {
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()){
                File predictionFile = new File(root, "prediction.txt");
                FileWriter predictionWriter = new FileWriter(predictionFile);
                out = new BufferedWriter(predictionWriter);
            }
        } catch (IOException e) {
            Log.e("TAG", "Could not write file " + e.getMessage());
        }

        try {
            FileInputStream arffFile = new FileInputStream(new File(arffName));
            //InputStream arffFile = context.openFileInput(arffName); //context.getAssets().open(arffName);
            //InputStream arffFile = context.getAssets().open("features.arff");
            Log.e("TESTING",arffFile.toString());
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(arffFile);
            instances = source.getDataSet();
//            System.out.println(instances.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int s=0;
        int f=0;

        instances.setClassIndex(67);

        for (int i=0; i<instances.size();i++){
            instances.instance(i).setClassMissing();
            double value = 0.0;
            try {
                String tmp = "";
                value = cls.classifyInstance(instances.instance(i));

            }catch(Exception e){
                e.printStackTrace();
            }

            String prediction = instances.classAttribute().value((int) value);
            predictionArray.add(prediction);

            try {
                out.write(prediction+"\n");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try{
            out.close();
            Log.e("TESTING", "out closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
            return getSplitTimes(predictionArray);
        }

        //get prediction from instance
    public String getPrediction(double x, double y, double z) throws Exception {
        instance = createInstance(x, y, z);
        double predictionValue = cls.classifyInstance(instance.firstInstance());
        double[] destributionValue = cls.distributionForInstance(instance.firstInstance());
        String prediction = instance.classAttribute().value((int) predictionValue);

        return prediction;
    }

    //get prediction from list
    public String getPrediction(ArrayList<SensorData> list) throws Exception {
        Map exerciseMap = new HashMap();

        for (SensorData dataPoint : list){
            double x = dataPoint.getData()[0];
            double y = dataPoint.getData()[1];
            double z = dataPoint.getData()[2];

            instance = createInstance(x, y, z);
            double predictionValue = cls.classifyInstance(instance.firstInstance());

            int count = (int)(exerciseMap.containsKey(predictionValue) ? exerciseMap.get(predictionValue) : 0);
            exerciseMap.put(predictionValue, count + 1);
        }

        return null;
    }

    public Instances createInstance(double x, double y, double z){
        //crete attribute list
        Attribute a1 = new Attribute("x");
        Attribute a2 = new Attribute("y");
        Attribute a3 = new Attribute("z");
        ArrayList<String> exerciseList = new ArrayList<String>();
        exerciseList.add("10 Push up");
        exerciseList.add("10 Sit up");
        exerciseList.add("5 Squat Jumps");
        Attribute a4 = new Attribute("exercise",exerciseList);
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(a1);
        attributeList.add(a2);
        attributeList.add(a3);
        attributeList.add(a4);

        // Create an empty training set
        Instances in = new Instances("ex", attributeList, 10);
        // Set class index
        in.setClassIndex(3);

        // Create the instance
        Instance instance = new DenseInstance(4);
        instance.setValue(0,x);
        instance.setValue(1,y);
        instance.setValue(2,z);

        // add the instance
        in.add(instance);

        return new Instances(in);
    }

    public ArrayList<Long> getSplitTimes(ArrayList<String> predictionList){
        loadTimestamps();

        int max = 0, s1= 0, s2 = 0;
        long start, end;
        start = System.currentTimeMillis();
        for(int i = 1; i < predictionList.size()-1; i++ ){
            for(int j = i+1; j < predictionList.size(); j++){
                //Test score
                int tmp_score = getSplit(i, j, predictionList);

                if(tmp_score > max){
                    max = tmp_score;
                    s1 = i;
                    s2 = j;
                }
            }
        }
        end = System.currentTimeMillis();

        int j = 0;
        long startTime = timestamps.get(0);
        long endTime = timestamps.get(timestamps.size()-1);
        ArrayList<Long> splitTimes = new ArrayList<Long>();
        splitTimes.add(startTime);
        Log.e("TESTING"," start: "+splitTimes.get(0));
        for(int i = 0; i < predictionList.size(); i++){
            if(i < s1){
                j = 0;
                Log.e("TESTING"," "+splits[0]);
            }else if(i > s1 && i <s2){
                Log.e("TESTING", " " + splits[1]);
                if(j == 0){
                    splitTimes.add(timestamps.get(i));
                    Log.e("TESTING", " split1: " + splitTimes.get(1));
                }
                j = 1;
            }else if(i> s2){
                Log.e("TESTING"," "+splits[2]);
                if(j == 1){
                    splitTimes.add(timestamps.get(i));
                    Log.e("TESTING", " split2: " + splitTimes.get(2));
                }
                j = 2;
            }
        }
        Log.e("TESTING"," end: "+splitTimes.get(splitTimes.size()-1));
        splitTimes.add(endTime);
        Log.e("TESTING", " " + splitTimes.toString());

//        long JumpUp_diff = splitTimes.get(1)- splitTimes.get(0);
//        long Frontal_diff = splitTimes.get(2)- splitTimes.get(1);
//        long Knees_diff = splitTimes.get(3)- splitTimes.get(2);
//
//        //Total time
//        long totalTime = endTime-startTime;

        return splitTimes;
    }

    public void loadTimestamps(){
        try {
            Scanner scanner = new Scanner(new File("/storage/emulated/0/timestamps.csv"));

            int i = 0;
            while(scanner.hasNext()){
                String tmp = scanner.nextLine();
                String[] data = tmp.split(",");

                i++;
                if(i % 64 == 0){
                    Log.e("TESTING","timestamp:"+data[3]);
                    timestamps.add(Long.parseLong(data[3]));
                }
            }
            Log.e("TESTING","timestamp loaded");

            scanner.close();
        } catch (FileNotFoundException e) {
            Log.e("TESTING", "timestamp not found");
        }
    }

    public int getSplit(int s1, int s2, ArrayList<String> predictionList){
        int score = 0;

        for(int i = 0; i < predictionList.size(); i++){
            String tmp = predictionList.get(i);
            if(i < s1 && tmp.equals(splits[0])){
                score++;
            }else if(i > s1 && i <s2 && tmp.equals(splits[1])){
                score++;
            }else if(i> s2 && tmp.equals(splits[2])){
                score++;
            }
        }

        return score;
    }
}
