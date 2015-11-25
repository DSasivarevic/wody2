package models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kevin on 21-09-2015.
 */
public class WOD {
    private String name;
    private String description;
    private int rounds;
    private Date date;
    private String sDate;
    private boolean isBenchmark;
    private long startTime;
    private long endTime;
    private CopyOnWriteArrayList<BaseExercise> exercises = new CopyOnWriteArrayList<BaseExercise>();
    private ArrayList<TimeExercise> timeExercises = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<String>();
    private int currentExercise = 0;


    public int getCurrentExercise() {
        return currentExercise;
    }

    public int nextExercise(){
        //if(currentExercise < exercises.size()){
            return currentExercise++;
        //s}
        //return -1;
    }


    public WOD(String name, String description, int rounds){
        this.name = name;
        this.description = description;
        this.rounds = rounds;
        date = new Date();
    }

    public void start(){
        startTime = System.currentTimeMillis();
    }
    public void stop(){
        endTime = System.currentTimeMillis();
    }



    public void addNote(String txt){
        getNotes().add(txt);
    }

    public void addExercise(BaseExercise ex){
        getExercises().add(ex);
    }

    public void addTimeExercise(TimeExercise ex){
        timeExercises.add(ex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBenchmark() {
        return isBenchmark;
    }

    public void setIsBenchmark(boolean isBenchmark) {
        this.isBenchmark = isBenchmark;
    }

    public long getStartTime() {
        return startTime;
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

    public CopyOnWriteArrayList<BaseExercise> getExercises() {
        return exercises;
    }

    public ArrayList<TimeExercise> getTimeExercises(){
        return timeExercises;
    }

    public void setExercises(CopyOnWriteArrayList<BaseExercise> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "{name: " + name + "}";
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }
}
