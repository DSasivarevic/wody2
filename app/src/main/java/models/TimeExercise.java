package models;

import java.util.ArrayList;

/**
 * Created by Kevin on 21-09-2015.
 */
public class TimeExercise extends BaseExercise {
    private long startTime;
    private long endTime;
    ArrayList<SensorData> sensorData = new ArrayList<SensorData>();
    private int repetitions;

    public TimeExercise(String name, int repetitions){
        this.name = name;
        this.repetitions = repetitions;
    }

    public void start(){
        setStartTime(System.currentTimeMillis());
    }

    public void stop(){
        setEndTime(System.currentTimeMillis());
    }

    public void addData(SensorData data){
        this.sensorData.add(data);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getDuration(){
        return endTime - startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void addSensorData(SensorData data){
        this.sensorData.add(data);
    }

    public ArrayList<SensorData> getSensorData(){
        return  this.sensorData;
    }

    public int getRepetitions(){
        return repetitions;
    }
}
