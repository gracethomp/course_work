package com.example.course_work.calender.eventType;

import com.example.course_work.calender.eventCategory.Category;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event implements Serializable{
    private String name;
    private transient LocalDate date;
    private transient Category category;
    private String description;
    private transient Notification notification;
    private static final ArrayList<Event> arrayList = new ArrayList<>();
    //String filename = "event.dat";
    public Event(){
        this.notification = new Notification();//композиция
        this.category = new Category("");
    }
    public Event(String curName, LocalDate curDate, Category curCategory, String curDescription) {
        name = curName;
        date = curDate;
        //category = curCategory;//агрегация
        description = curDescription;
    }
    public String getName()
    {
        return name;
    }
    public LocalDate getDate()
    {
        return date;
    }
    public Category getCategory()
    {
        return this.category;
    }
    public String getDescription()
    {
        return description;
    }
    public void setName(String currentName) {
        this.name = currentName;
    }
    public void setDate(LocalDate currentDate) {
        this.date = currentDate;
    }
    public void setCategory(Category currentCategory) {
        this.category = currentCategory;
    }
    public void setDescription(String currentDesc) {
        description = currentDesc;
    }
    public void add(Event event1) {
        arrayList.add(event1);
        notification.setNotification();
    }
    public void edit(int index, Event changedEvent) {
        arrayList.set(index, changedEvent);
        notification.setNotification();
    }
    public void deleteEvent(Event event){
        arrayList.remove(event);
    }
    public void search(String currentName) {
        Map<String, Event> eventMap = new HashMap<>();
        for(Event event: arrayList) {
            eventMap.put(event.getName(), event);
        }
        eventMap.get(currentName);
    }
    public List<Event> dateSearch(LocalDate date) {
        List<Event> dateEvents = new ArrayList<>();
        for(Event event: arrayList) {
            if(event.date == date) {
                dateEvents.add(event);
            }
        }
        return dateEvents;
    }

    public void print() {
        System.out.println(this.name);
        System.out.println(this.date);
        System.out.println(this.category.categoryName);
        System.out.println(this.description);
        if(notification.getIsNotification()){
            //галочка есть
        }
        else {
            //галочки нет
        }
    }
    public static ArrayList<Event> getArrayListEvent() {
        return arrayList;
    }
    public String eventToString(){
        //  String time = time.toString(); // add time
        //  String str = time+" "+name;
        String str =name;
        return str;
    }

    public Event findByName(String whatToFind) {
        Event eventFound = new Event();
        for (Event event: arrayList) {
            if(event.name.equals(whatToFind)) {
                eventFound = event;
            }
        }
        return eventFound;
    }
    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", category=" + category.categoryName +
                ", description='" + description + '\'' +
                ", notification=" + notification +
                '}';
    }
}

