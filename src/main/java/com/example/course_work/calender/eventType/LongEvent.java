package com.example.course_work.calender.eventType;
import com.example.course_work.calender.eventCategory.Category;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class LongEvent extends Event implements Serializable {
    private TimeSet timeSet;
    private String location;
    private final ArrayList<LongEvent> arrayList = new ArrayList<>();
    public LongEvent() throws IOException {
        super();
        this.timeSet = new TimeSet();
    }
    public LongEvent(String currentName, LocalDate currentDate, Category currentCategory, String currentDescription, String curLocation) throws IOException {
        super(currentName, currentDate, currentCategory, currentDescription);
        this.location = curLocation;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setTimeSetLE() {
        timeSet.setTimeSet();
    }
    public void add() throws IOException {
        //this.setTimeSetLE();
        arrayList.add(this);
    }
    public void edit(int index, LongEvent changedEvent) {
        arrayList.set(index, changedEvent);
    }
    public void delete(){
        arrayList.remove(this);
    }
    public ArrayList getArrayListLE() {
        return arrayList;
    }
    @Override
    public void print() {
        super.print();
        System.out.println(location); //поменяла на лн
        this.timeSet.printTimeSet();
    }
}
