package models;

import java.util.ArrayList;

/**
 * Created by Kevin on 21-09-2015.
 */
public abstract class BaseExercise {
    String name;
    String description;
    ArrayList<Muscle> muscles = new ArrayList<Muscle>();

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

    public ArrayList<Muscle> getMuscles() {
        return muscles;
    }

    public void setMuscles(ArrayList<Muscle> muscles) {
        this.muscles = muscles;
    }


    public void addMuscle(Muscle m){
        muscles.add(m);
    }

}
