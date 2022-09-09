package com.example.course_work.calender.calendarView;

import com.example.course_work.calender.CalenderFacade;
import com.example.course_work.calender.eventType.Event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

public class WeekView implements View {
    private int year;
    private Month month;
    private LocalDate monday;
    private Slot[] slots; // think about this

    public WeekView(LocalDate curDay, int w, int l){
        while (curDay.getDayOfWeek()!=DayOfWeek.MONDAY){  //maybe add option to begin with SUNDAY later
            curDay=curDay.minusDays(1);
        }
        monday = curDay;
        month=curDay.getMonth();
        year=curDay.getYear();
        slots=new Slot[7];
        for(int i=0; i<7; i++){
            slots[i]=new Slot(curDay,w,l);
            curDay=curDay.plusDays(1);
        }
    }

    public static Slot[] DefaultView(LocalDate curDay, int w, int l){
        WeekView thisWeek= new WeekView(curDay, w, l);
        return thisWeek.slots;
    }

    public static Slot[] NextWeek(LocalDate curDay, int w, int l){
        WeekView thisWeek= new WeekView(curDay.plusDays(7), w, l);
        return thisWeek.slots;
    }

    public static Slot[] PreviousWeek(LocalDate curDay, int w, int l){
        WeekView thisWeek= new WeekView(curDay.minusDays(7), w, l);
        return thisWeek.slots;
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

    public LocalDate getMonday() {
        return monday;
    }

    public void setMonday(LocalDate monday) {
        this.monday = monday;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }

    private void print_daysOfWeek (Slot[] days, int w, int l){
        for (int i=0; i<7; i++) {
            String day= String.valueOf(days[i].getDayOfWeek());
            String str = String.format(" %-30s", day);
            System.out.print(str);
        }
        System.out.printf("\n");
    }

    private void print_dates0fWeek (Slot[] days, int w, int l){
        for (int i=0; i<7; i++) {
            LocalDate date=days[i].getDate();
            String str = String.format(" %-30s", date);
            System.out.print(str);
        }
        System.out.println("\n");
    }

    private void print_listOfEventsForWeek (Slot[] days, int w, int l){ // for Week and Month View
        String space = String.format("%-30s|", "...");
        String s1;
        for (int j = 0; j < l; j++) { // l is max amount if events to show
            for (int i=0; i<7; i++) {  // to write horizontal first
                if (days[i].getDate().getMonth()==month) { // only print events for a day if this month
                    List<Event> printEvents = days[i].getTodayEvents();
                    if (!printEvents.isEmpty()) {
                        try {
                            s1 = printEvents.get(j).getName();
                        } catch ( IndexOutOfBoundsException e ) {
                            s1 = " ";
                        }
                        String str = String.format("%-30s|",s1); // string event
                        System.out.print(str);
                        /*if (l < printEvents.size()) {
                            System.out.print(space);
                        }
                        else if (l > printEvents.size()) {
                            System.out.println();
                        }*/
                    }
                    else{
                        String str2 = String.format("%-30s|"," ");
                        System.out.print(str2);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printAll(Slot[] days, LocalDate cur, int w, int l){

        print_daysOfWeek(days,1,1);
        print_dates0fWeek(days,1,1);
        print_listOfEventsForWeek(days,1,l); //l = maximum of events displayed

        System.out.println("PREVIOUS WEEK (input 'prev') >>> CURRENT WEEK >>> NEXT WEEK (input 'next') ----- EXIT ('exit')::");

        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()) {
            String in = scan.nextLine();

            switch (in) {
                case "prev":
                    days = PreviousWeek(cur, 1, 1); //view previous
                    CalenderFacade.clearScreen();
                    printAll(days, cur.minusDays(7), 1, l);
                    break;
                case "next":
                    days = NextWeek(cur, 1, 1); //view next
                    CalenderFacade.clearScreen();
                    printAll(days, cur.plusDays(7), 1, l);
                    break;
                case "exit":
                    CalenderFacade.clearScreen();
                    FacadeForView.switchView();
                    break;
                default:
                    System.out.println("Default view is opened");
                    break;
            }
        }
    }

    private void countDaysOfWeek (Slot[] days, int w, int l){
        for (int i=0; i<7; i++) {
            String day= String.valueOf(days[i].getDayOfWeek());
            String str = String.format(" %-30s", day);
            System.out.print(str);
        }
        System.out.printf("\n");
    }

    @Override
    public void displayView(){
        CalenderFacade.clearScreen();
        LocalDate cur =LocalDate.now();
        Slot[] days = DefaultView(cur,1,1);
        printAll(days,cur,1,10);
        FacadeForView.switchView();
    }
    @Override
    public void reset() {
    }
}
