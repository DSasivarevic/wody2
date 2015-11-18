package models;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public ArrayList<String> getPrediction(Context context, String arffName, int k){
        Instances instances = null;
        ArrayList<String> predictionArray = new ArrayList<String>();
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

//        int tempInstanceCount = 0;
//        Map<Double,Double> tempList = new HashMap<>();
//        tempList.put(0.0,0.0);
//        tempList.put(1.0,0.0);
//        tempList.put(2.0,0.0);
        instances.setClassIndex(67);
//        Log.e("TESTING", "" + instances.classAttribute().enumerateValues());
//        instances.classAttribute().numValues();


        for (int i=0; i<instances.size();i++){
//            double[] destributionValue = null;
            instances.instance(i).setClassMissing();
            double value = 0.0;
            try {
//                destributionValue = cls.distributionForInstance(instances.get(i));
                String tmp = "";
                value = cls.classifyInstance(instances.instance(i));

            }catch(Exception e){
                e.printStackTrace();
            }

//            //prediction based on 3 points - only for testing ----------------
//            if(tempInstanceCount < 4){
//                tempInstanceCount++;
//            }else{
//                tempInstanceCount = 0;
//                double predictionValue = 0.0;
//
//                double maxValueInMap=(Collections.max(tempList.values()));
//                for (Map.Entry<Double, Double> entry : tempList.entrySet()) {  // Itrate through hashmap
//                    if (entry.getValue()==maxValueInMap) {
//                        predictionValue = entry.getKey();     // Print the key with max value
//                    }
//                }
////                System.out.println(tempList.toString());
//                System.out.println("Improved prediction: "+instances.classAttribute().value((int) predictionValue));
//
//                tempList.put(0.0, 0.0);
//                tempList.put(1.0, 0.0);
//                tempList.put(2.0, 0.0);
//            }
//
//                tempList.put(0.0,tempList.get(0.0)+ destributionValue[0]);
//                tempList.put(1.0,tempList.get(1.0)+ destributionValue[1]);
//                tempList.put(2.0,tempList.get(2.0)+ destributionValue[2]);
//
//            //-------------------------------------------------------------

            String prediction = instances.classAttribute().value((int) value);
            predictionArray.add(prediction);

            if(instances.get(i).toString(3).equals(prediction) ){
                s++;
                System.out.println("TRUE ------ real: "+ instances.get(i).toString(3)+ "   prediction: "+prediction);
            }else if(instances.get(i).toString(3).equals("'"+prediction+"'") ){
                s++;
                System.out.println("True ------ real: "+ instances.get(i).toString(3)+ "   prediction: "+prediction);
            }else {//!originalTrain.get(i).toString(3).equals("Push-ups")) {
                f++;
                System.out.println("FALSE ------ real: " + instances.get(i).toString(3) + "   prediction: " + prediction);
            }
        }

        System.out.println("true: "+s);
        System.out.println("false: " + f);

        return predictionArray;
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

}
