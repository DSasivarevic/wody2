package models;

import java.io.InputStream;
import java.util.ArrayList;

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
    private InputStream wodModel;
    private Classifier cls = null;
    private Instances instance = null;

    public ExercisePrediction(InputStream stream) {
        wodModel = stream;

        //ceate classifier
        try {
            cls = (Classifier) weka.core.SerializationHelper.read(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get prediction from arff
    public void getPrediction(InputStream arff){
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
        Instances instances = null;
        try {
            instances = source.getDataSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int s=0;
        int f=0;
        for (int i=0; i<instances.size();i++){
            instances.setClassIndex(3);
            double value = 0.0;
            try {
                cls.classifyInstance(instances.instance(i));

                value = cls.classifyInstance(instances.instance(i));
            }catch(Exception e){
                e.printStackTrace();
            }

            String prediction = instances.classAttribute().value((int) value);

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
    }

    //get prediction from instance
    public String getPrediction(double x, double y, double z) throws Exception {
        instance = createInstance(x, y, z);
        double predictionValue = cls.classifyInstance(instance.firstInstance());
        double[] destributionValue = cls.distributionForInstance(instance.firstInstance());

        System.out.println("prediction value: " + predictionValue);
        String prediction = instance.classAttribute().value((int) predictionValue);


        System.out.println("The predicted value of instance: " + prediction);

        return prediction;
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
