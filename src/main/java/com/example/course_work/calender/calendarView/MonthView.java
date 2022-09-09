package com.example.course_work.calender.calendarView;

import com.example.course_work.calender.CalenderFacade;
import com.example.course_work.calender.eventType.Event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

import static java.time.DayOfWeek.*;

public class MonthView implements View {
    private int year;
    private Month month;
    private int len;
    private Slot[] slots;

    public MonthView(LocalDate curDay, int w, int l){  // w l: grid size
        year=curDay.getYear();
        month=curDay.getMonth();
        len=curDay.lengthOfMonth();
        slots = new Slot[len];
        LocalDate currentDay=LocalDate.of(year,month,1);
        for (int i=0; i<len; i++){
            slots[i]=new Slot(currentDay,w,l);
            currentDay=currentDay.plusDays(1);
        }
    }

    public static Slot[] DefaultView(LocalDate cur, int w, int l){
        MonthView thisMonth = new MonthView(cur,w,l);
        return thisMonth.slots;
    }

    public static Slot[] NextMonth(LocalDate curDay, int w, int l){
        curDay=curDay.plusMonths(1);
        MonthView thisMonth = new MonthView(curDay,w,l);
        return thisMonth.slots;
    }

    public static Slot[] PreviousMonth(LocalDate curDay, int w, int l){
        curDay=curDay.minusMonths(1);
        MonthView thisMonth = new MonthView(curDay,w,l);
        return thisMonth.slots;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getDays() {
        return len;
    }

    public void setDays(int len) {
        this.len = len;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }

    private void print_month(LocalDate curDay, int w, int l){
        String str = String.format("%70s", curDay.getMonth());
        System.out.println(str);
    }

    private void print_daysOfWeek (Slot[] days, int w, int l){
        for (int i=0; i<7; i++) {
            String day= String.valueOf(days[i].getDayOfWeek());
            String str = String.format(" %-20s", day);
            System.out.print(str);
        }
        System.out.println();
    }

    private void print_dates0fWeek (Slot[] days,Month month, int w, int l){
        for (int i=0; i<7; i++) {
            LocalDate date=days[i].getDate();
            String str;
            if (date.getMonth()==month){
                str = String.format("%-20s|", date.getDayOfMonth());
            }
            else {
                str = String.format("%-20s ", "-");
            }
            System.out.print(str);
        }
        System.out.println();
    }

    private void print_listOfEventsForWeek (Slot[] days,Month month, int w, int l){ // for Week and Month View
        String space = String.format("%-20s|", "...");
        String s1;
        for (int j = 0; j < l; j++) { // write to (l) lines one by one (no matter the day)
            for (int i=0; i<7; i++) {  // write to day by day of the week
                if (days[i].getDate().getMonth()==month) { // only print if day is in this month
                    List<Event> printEvents; //initialize array of events for the day
                    printEvents = days[i].getTodayEvents();
                    if (!printEvents.isEmpty()) { //if the array of events for this day is not empty
                        try {
                            s1 = printEvents.get(j).getName();
                        } catch ( IndexOutOfBoundsException e ) {
                            s1 = " ";
                        }
                        String str = String.format("%-20s|", s1); // name
                        System.out.print(str); // print event
                       /* if (l < printEvents.size()) { //if the height is less than amount of events for the day
                            System.out.print(space); // add space
                        }
                        else if (l > printEvents.size()){
                            System.out.println();
                        }*/
                    }
                    else{
                        String str2 = String.format("%-20s|"," ");
                        System.out.print(str2);
                    }
                }
                else {
                    String str3 = String.format("%-20s "," ");
                    System.out.print(str3);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printAll (Slot[] days, LocalDate cur, int w, int l){
        Slot[] daysWeek = WeekView.DefaultView(cur,1,1);
        Month month = days[0].getDate().getMonth();
        LocalDate curDay = cur;
        print_month(curDay,1,1);
        print_daysOfWeek(daysWeek,1, 1);

        print_dates0fWeek(daysWeek,month , 1, 1); //first week
        print_listOfEventsForWeek(daysWeek,month, 1, l); //l = maximum of events displayed

        while(curDay.getMonth()==month){
            daysWeek = WeekView.NextWeek(curDay,1,1);
            curDay=curDay.plusDays(7);
            print_dates0fWeek(daysWeek,month, 1, 1);
            print_listOfEventsForWeek(daysWeek,month, 1, l); //l = maximum of events displayed
        }

        System.out.println("PREVIOUS MONTH (input 'prev') >>> CURRENT MONTH >>> NEXT MONTH (input 'next') ----- EXIT ('exit'):");

        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()) {
            String in = scan.nextLine();
            System.out.println(in);
            switch (in) {
                case "prev":
                    CalenderFacade.clearScreen();
                    days = PreviousMonth(cur, 1, 1); //view previous
                    cur = cur.minusMonths(1);
                    printAll(days, cur, 1, l);
                    break;
                case "next":
                    CalenderFacade.clearScreen();
                    days = NextMonth(cur, 1, 1); //view next
                    cur = cur.plusMonths(1);
                    printAll(days, cur, 1, l);
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
    public static int countDay(Slot[] days) {
        Month month = days[1].getDate().getMonth();
        int year = days[1].getDate().getYear();
        LocalDate first = LocalDate.of(year, month, 1);
        if (first.getDayOfWeek() == MONDAY) return 0;
        else if (first.getDayOfWeek() == TUESDAY) return 1;
        else if (first.getDayOfWeek() == WEDNESDAY) return 2;
        else if (first.getDayOfWeek() == THURSDAY) return 3;
        else if (first.getDayOfWeek() == FRIDAY) return 4;
        else if (first.getDayOfWeek() == SATURDAY) return 5;
        else if (first.getDayOfWeek() == SUNDAY) return 6;
        else return 0;
    }

    @Override
    public void displayView() {
        CalenderFacade.clearScreen();
        LocalDate cur = LocalDate.now();
        Slot[] days =DefaultView(cur,1,1);
        LocalDate cur1 = days[0].getDate();
        printAll(days,cur1,1,3);
        FacadeForView.switchView();
    }
    @Override
    public void reset() {
    }
}
