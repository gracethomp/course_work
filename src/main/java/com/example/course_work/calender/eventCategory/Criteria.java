package com.example.course_work.calender.eventCategory;

import com.example.course_work.calender.eventType.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface Criteria {
    public List<Event> typeCriteria(List<Event> eventList, String categoryName);

    public static List<Event> dateCriteria(ArrayList<Event> eventList, LocalDate curDate) {
        return null;
    }
}

