package com.example.course_work.calender.calendarView;

import com.example.course_work.calender.CalenderFacade;

import java.time.LocalDate;
import java.util.Scanner;

public class FacadeForView {
    private View monthly;
    private View weekly;
    private View daily;

    public FacadeForView(){
        this.monthly=new MonthView(LocalDate.now(), 1, 1); //for now: when switching views the calendar resets to today automatically
        this.weekly=new WeekView(LocalDate.now(), 1, 1);
        this.daily=new DayView(LocalDate.now(), 1,1);

    }
    public void displayMonth(){
        monthly.displayView();
    }
    public void displayWeek(){
        weekly.displayView();
    }
    public void displayDay(){
        daily.displayView();
    }
    public void resetMonth(){
        monthly.reset();
    }
    public void resetWeek(){
        weekly.reset();
    }
    public void resetDay(){
        daily.reset();
    }

    public static void switchView(){
        System.out.println("SWITCH VIEW: month, week, day : ");
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()) {
            String in = scan.nextLine();
            System.out.println(in);
            switch (in) {
                case "month":
                    CalenderFacade.ViewType m = CalenderFacade.ViewType.month;
                    CalenderFacade.switchView(m);
                    break;
                case "week":
                    CalenderFacade.ViewType w = CalenderFacade.ViewType.week;
                    CalenderFacade.switchView(w);
                    break;
                case "day":
                    CalenderFacade.ViewType d = CalenderFacade.ViewType.day;
                    CalenderFacade.switchView(d);
                    break;
                default:
                    System.out.println("Error: invalid input");
            }
        }
    }
}
