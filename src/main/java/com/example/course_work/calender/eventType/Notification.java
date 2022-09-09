package com.example.course_work.calender.eventType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notification implements Serializable {
    private boolean isNotification;
    private LocalDate date;
    private LocalDateTime time;
    public LocalDate getDate() {
        return this.date;
    }
    public LocalDateTime getTime() {
        return this.time;
    }
    public void setDate(LocalDate curDate){
        this.date = curDate;
    }
    public void setTime(LocalDateTime curTime){
        this.time = curTime;
    }
    public void setNotification(){
        if(isNotification) {
            // notification will be set
        }
    }
    public boolean getIsNotification(){
        return isNotification;
    }
}
