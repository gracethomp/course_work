package com.example.course_work.calender.eventCategory;

import com.example.course_work.calender.eventType.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CriteriaCategory implements Criteria {
    public List<Event> typeCriteria(List<Event> eventList, String categoryName) {
        List<Event> chosenEvents = new ArrayList<>();
        for(Event event: eventList) {
            if(event.getCategory().categoryName.equalsIgnoreCase(categoryName)) {
                chosenEvents.add(event);
            }
        }
        return chosenEvents;
    }

    public static List<Event> dateCriteria(ArrayList<Event> eventList, LocalDate curDate) {
     List<Event> dayEvents = new ArrayList<>();
     for (Event event : eventList) {
         if(event.getDate().equals(curDate)) {
             dayEvents.add(event);
         }
     }
     dayEvents.sort(Comparator.comparing(Event::getDate));
     return dayEvents;
    }

}
