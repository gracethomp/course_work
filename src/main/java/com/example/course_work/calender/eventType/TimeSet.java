package com.example.course_work.calender.eventType;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Scanner;

public class TimeSet implements Serializable {
    boolean isTimeSet;
    LocalTime startTime;
    LocalTime endTime;
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public void setTimeSet() {
        //выбор и установка даты
        Scanner in = new Scanner(System.in);
        int min = in.nextInt(),
                hour = in.nextInt(),
                min1 = in.nextInt(),
                hour1 = in.nextInt();
        startTime = LocalTime.of(hour, min);
        endTime = LocalTime.of(hour1, min1);
    }
    public void printTimeSet() {
        System.out.print(startTime);
        System.out.print(endTime);
    }
}
