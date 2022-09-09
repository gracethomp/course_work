package com.example.course_work.calender.calendarView;


import com.example.course_work.calender.CalenderFacade;
import com.example.course_work.calender.eventType.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DayView implements View {
    private Slot slot;

    public DayView(LocalDate curDay, int w, int l){
        slot=new Slot(curDay,w,l);
    }

    public static Slot NextDay(LocalDate curDay, int w, int l){
        DayView thisDay = new DayView(curDay.plusDays(1),w,l);
        return thisDay.slot;
    }

    public static Slot PreviousDay(LocalDate curDay, int w, int l){
        DayView thisDay = new DayView(curDay.minusDays(1),w,l);
        return thisDay.slot;
    }

    public static Slot DefaultView(LocalDate curDay,int w, int l){
        DayView thisDay = new DayView(curDay, w, l);
        return thisDay.slot;
    }

    public static void clearScreen() { // move to facade?
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void print_date (Slot printDay, int w, int l){
        String str = String.format("%50s", printDay.getDate());
        System.out.println(str);
    }

    private void print_dayOfWeek(Slot printDay, int w, int l){
        String str = String.format("%50s", printDay.getDate().getDayOfWeek());
        System.out.println(str);
    }

    public void print_listOfEvents (Slot printDay, int w, int l){ // for Day View
        List<Event> printEvents = printDay.getTodayEvents();
        for (int i=0; i<l; i++){
            if (!printEvents.isEmpty()) {
                String str = String.format("|%50s|", printEvents.get(i).eventToString()); // string event
                System.out.println(str);
            }
            else{
                String str2 = String.format("|%50s|"," ");
                System.out.println(str2);
            }
        }
        System.out.println();
    }

    private void printAll(Slot day, LocalDate cur, int w, int l){

        print_dayOfWeek(day,1,1);
        print_date(day,1,1);
        print_listOfEvents(day,1,l); //l = maximum of events displayed

        System.out.println("PREVIOUS DAY(input 'prev') >>> CURRENT DAY >>> NEXT DAY(input 'next') ----- EXIT ('exit')::");

        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()) {
            String in = scan.nextLine();
            switch (in) {
                case "prev":
                    day = PreviousDay(cur, 1, 1); //view previous
                    clearScreen();
                    printAll(day, cur.minusDays(1), 1, l);
                    break;
                case "next":
                    day = NextDay(cur, 1, 1); //view next
                    clearScreen();
                    printAll(day, cur.plusDays(1), 1, l);
                    break;
                case "exit":
                    CalenderFacade.clearScreen();
                    FacadeForView.switchView();
                    break;
                default:
                    System.out.println("Default view is opened");
            }
        }
    }


    @Override
    public void displayView() {
        CalenderFacade.clearScreen();
        LocalDate cur =LocalDate.now();
        Slot day = DefaultView(cur,1,1);
        int l=day.getTodayEvents().size();
        printAll(day,cur,1,l);
        FacadeForView.switchView();
    }

    @Override
    public void reset() {
    }
}
