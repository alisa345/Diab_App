package com.alisa.diabet.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private String time;
    private String eating;
    private String insulin_length;
    private String injected_insulin;
    private String weight;
    private String bread_units;
    private String wellbeing;
    private String description;

    private String sugar_level;
    private int priority;

    public Note(String sugar_level, String description, String date, String time, String eating,
                String insulin_length, String injected_insulin, String weight, String bread_units,
                String wellbeing, int priority) {
        this.sugar_level = sugar_level;
        this.description = description;
        this.date = date;
        this.time = time;
        this.eating = eating;
        this.insulin_length = insulin_length;
        this.injected_insulin = injected_insulin;
        this.weight = weight;
        this.bread_units = bread_units;
        this.wellbeing = wellbeing;
        this.priority = priority;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getEating() {
        return eating;
    }

    public int getPriority() {
        return priority;
    }

    public String getInsulin_length() {
        return insulin_length;
    }

    public String getInjected_insulin() {
        return injected_insulin;
    }

    public String getWeight() {
        return weight;
    }

    public String getBread_units() {
        return bread_units;
    }

    public String getWellbeing() {
        return wellbeing;
    }

    public String getSugar_level() {
        return sugar_level;
    }
}
