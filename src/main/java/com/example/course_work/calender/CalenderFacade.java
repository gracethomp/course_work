package com.example.course_work.calender;

import com.example.course_work.calender.calendarView.FacadeForView;
import com.example.course_work.calender.eventCategory.CriteriaCategory;
import com.example.course_work.calender.eventType.Event;
import com.example.course_work.calender.eventType.SaveData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;


public class CalenderFacade implements Serializable {
    private final CriteriaCategory category = new CriteriaCategory();
    String filename = "event.dat";

    public CalenderFacade() throws IOException {
    }

    public enum ViewType{
        month,week,day
    }

    FileOutputStream outputStream = new FileOutputStream(filename);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

    public void createEvent() throws IOException {
        Scanner inp = new Scanner(System.in);
        int var = inp.nextInt();
        SaveData sD = new SaveData();
        switch (var) {
            case (1) : sD.addEvent();
            break;

            case (2) : sD.addLE();
            break;

            case (3) : sD.addTask();
            break;
            }
       // inp.close();
    }

    public void selectEvent() throws IOException, ClassNotFoundException {
        Scanner inp = new Scanner(System.in);
        Event event = new Event();
        int var = inp.nextInt();
        int indexOfEvent = inp.nextInt();
        SaveData sD = new SaveData();
        sD.editEvent(indexOfEvent, var);
    //    inp.close();
        event.search(event.getName());
        event.deleteEvent(event);
    }

    public static void startView(){
        FacadeForView facadeForView = new FacadeForView();
        facadeForView.displayMonth();
    }

    public static void switchView(ViewType type){
        FacadeForView facadeForView = new FacadeForView();
        switch (type) {
            case month: facadeForView.displayMonth();
                break;
            case week: facadeForView.displayWeek();
                break;
            case day: facadeForView.displayDay();
                break;
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public void applyFilter(){
        String curCatName = ""; //выбор имени категории
        //category.eventCriteria(list, curCatName);
    }

    public void exit(){
        System.exit(1);
    }
}
