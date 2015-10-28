package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kevin on 21-09-2015.
 */
public class WOD {
    private String name;
    private String description;
    private int rounds;
    private Date date;
    private boolean isBenchmark;
    private long startTime;
    private long endTime;
    private ArrayList<BaseExercise> exercises = new ArrayList<BaseExercise>();
    private ArrayList<String> notes = new ArrayList<String>();
    private int currentExercise = 0;


    public int getCurrentExercise() {
        return currentExercise;
    }

    public int nextExercise(){
        if(currentExercise < exercises.size()-1){
            return currentExercise++;
        }
        return -1;
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

    public ArrayList<BaseExercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<BaseExercise> exercises) {
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
}
