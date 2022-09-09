package com.example.course_work;

import com.example.course_work.calender.calendarView.DayView;
import com.example.course_work.calender.calendarView.MonthView;
import com.example.course_work.calender.calendarView.Slot;
import com.example.course_work.calender.calendarView.WeekView;
import com.example.course_work.calender.eventCategory.Category;
import com.example.course_work.calender.eventCategory.CriteriaCategory;
import com.example.course_work.calender.eventType.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Controller {
    public Tab viewWeek;
    public Tab viewMonth;
    public Tab viewDay;
    private LocalDate cur;
    private LocalDate curw;
    private LocalDate curd;
    ControllerEvent controllerEvent = new ControllerEvent();
    CriteriaCategory criteria = new CriteriaCategory();
    ObservableList<String> filtersList  = FXCollections.observableArrayList("Study", "Health", "Rest", "Default view");
    public static String name;
    public static boolean isEdited = false;

    @FXML
    private List<Label> labelListM;
    @FXML
    private List<Text> eventListM;
    @FXML
    private List<Text> evListD;
    @FXML
    private List<Text> descrList;
    @FXML
    private List<Text> eventListW;
    @FXML
    private List<Label> labelListW;
    @FXML
    private Button prev;
    @FXML
    private Button next;
    @FXML
    private Text monthName;
    @FXML
    private Text weekName;
    @FXML
    private Button prevWeek;
    @FXML
    private Button nextWeek;
    @FXML
    private Button prevDay;
    @FXML
    private Button nextDay;
    @FXML
    private Button refreshM;
    @FXML
    private Text dayName;
    @FXML
    private Button create;
    @FXML
    private ComboBox<String> comboBox;

    public Controller() throws IOException {
    }

    public void fillDatesMonth(Slot[] days){
        int start;
        start = MonthView.countDay(days);
        for (int i=0;i<days[1].getDate().lengthOfMonth();i++) {
            labelListM.get(i+start).setText(String.valueOf(i+1));
        }
    }

    public void fillEventsMonth(Slot[] days) {
        System.out.println("enter");
        int start;
        start = MonthView.countDay(days);
        for (int i = 1; i < 84; i=i+2) {
            if(i<start*2){
                eventListM.get(i - 1).setText(" ");
                eventListM.get(i).setText(" ");
            }
            else if (((i-1)/2-start)<days[1].getDate().lengthOfMonth()){
                List<Event> printEvents;
                printEvents = days[(i-1)/2-start].getTodayEvents();
                if (printEvents.size()==1) { //if the array of events for this day is not empty
                    try {
                        eventListM.get(i - 1).setText(printEvents.get(0).getName());
                    } catch (IndexOutOfBoundsException e) {
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    }
                }
                else if (printEvents.size()>1) { //if the array of events for this day is not empty
                    try {
                        eventListM.get(i - 1).setText(printEvents.get(0).getName());
                        eventListM.get(i).setText(printEvents.get(1).getName());
                    } catch (IndexOutOfBoundsException e) {
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    }
                }
                else{
                    eventListM.get(i - 1).setText(" ");
                    eventListM.get(i).setText(" ");
                }
            }
            else{
                eventListM.get(i - 1).setText(" ");
                eventListM.get(i).setText(" ");
            }
        }
        System.out.println("out");
    }

    public void fillEventsWeek(Slot[] days){
        for (int i = 1; i <= 7; i++) {
            List<Event> printEvents = days[i - 1].getTodayEvents();
            for (int j = 1; j <= 7; j++) {
                if (!printEvents.isEmpty()) {
                    try {
                        eventListW.get(7*(i-1)+j-1).setText(printEvents.get(j-1).getName());
                    } catch (IndexOutOfBoundsException e) {
                        eventListW.get(7*(i-1)+j-1).setText(" ");
                    }
                }
                else {
                    eventListW.get(7*(i-1)+j-1).setText(" ");
                }
            }
        }
    }

    public void fillEventsDay(Slot day){
        for (int i = 0; i < 8; i++) {
            List<Event> printEvents = day.getTodayEvents();
            if ((printEvents.size()>=1)) {
                try {
                    evListD.get(i).setText(printEvents.get(i).getName());
                    if(printEvents.get(i).getDescription()!=null){
                        descrList.get(i).setText(printEvents.get(i).getDescription());
                    }
                    else{
                        descrList.get(i).setText("-");
                    }
                } catch (IndexOutOfBoundsException e) {
                    evListD.get(i).setText(" ");
                    descrList.get(i).setText(" ");
                }
            }
            else{
                evListD.get(i).setText(" ");
                descrList.get(i).setText(" ");
            }
        }
    }

    public void refresh(){
        Slot[] daysm= MonthView.DefaultView(cur,1,1);
        fillEventsMonth(daysm);
        Slot[] daysw= WeekView.DefaultView(curw,1,1);
        fillEventsWeek(daysw);
        Slot day = DayView.DefaultView(curd,1,1);
        fillEventsDay(day);
    }
    public void fillDatesWeek(Slot[] days){
        for (int i=0;i<7;i++) {
            labelListW.get(i).setText(String.valueOf(days[i].getDate().getDayOfMonth()));
        }
    }
    public void monthYearName(Month month, int year){
        monthName.setText(month + " " + year);
    }

    public void setWeekName(Slot[] days){
        weekName.setText(days[0].getDate().getDayOfMonth() + " "
                + days[0].getDate().getMonth() + " "
                + days[0].getDate().getYear() + " - "
                + days[6].getDate().getDayOfMonth() + " "
                + days[6].getDate().getMonth() + " "
                + days[6].getDate().getYear());
    }

    public void setDayName(Slot day){
        dayName.setText(day.getDate().getDayOfMonth() + " "
                + day.getDate().getMonth() + " "
                + day.getDate().getYear());
    }
    public void clearMonth(){
        for (int i=0;i<42;i++) {
            labelListM.get(i).setText("-");
        }
    }
    public void nextMonth(){
        Slot[] days = MonthView.NextMonth(cur,1,1);
        clearMonth();
        fillDatesMonth(days);
        cur = cur.plusMonths(1);
        monthYearName(cur.getMonth(),cur.getYear());
        fillEventsMonth(days);
    }
    public void previousMonth(){
         Slot[] days = MonthView.PreviousMonth(cur,1,1);
         clearMonth();
         fillDatesMonth(days);
         cur = cur.minusMonths(1);
         monthYearName(cur.getMonth(),cur.getYear());
         fillEventsMonth(days);
    }

    public void previousWeek(){
        Slot[] days = WeekView.PreviousWeek(curw,1,1);
        fillDatesWeek(days);
        setWeekName(days);
        curw=curw.minusDays(7);
        fillEventsWeek(days);
    }

    public void nextWeek(){
        Slot[] days = WeekView.NextWeek(curw,1,1);
        fillDatesWeek(days);
        setWeekName(days);
        curw=curw.plusDays(7);
        fillEventsWeek(days);
    }

    public void previousDay(){
        Slot day = DayView.PreviousDay(curd,1,1);
        setDayName(day);
        curd=curd.minusDays(1);
        fillEventsDay(day);

    }

    public void nextDay(){
        Slot day = DayView.NextDay(curd,1,1);
        setDayName(day);
        curd=curd.plusDays(1);
        fillEventsDay(day);
    }

    public void createEvent() {
        create.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("version2.2_LA.fxml"));
            Scene secondScene = null;
            try {
                secondScene = new Scene(fxmlLoader.load(), 400, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage newWindow = new Stage();
            newWindow.setTitle("Create new event");
            newWindow.setScene(secondScene);
            newWindow.show();
        });
    }

    public void initialize() {
        cur = LocalDate.now();
        curw = LocalDate.now();
        curd = LocalDate.now();
        Slot[] daysm = MonthView.DefaultView(cur,1,1);
        Slot[] daysw = WeekView.DefaultView(cur,1,1);
        Slot day = DayView.DefaultView(cur,1,1);
        fillDatesMonth(daysm);
        monthYearName(cur.getMonth(),cur.getYear());
        fillDatesWeek(daysw);
        setWeekName(daysw);
        setDayName(day);
        fillEventsMonth(daysm);
        fillEventsWeek(daysw);
        fillEventsDay(day);

        //LA
        comboBox.setPromptText("Filters");
        comboBox.setEditable(true);
        comboBox.setItems(filtersList);
    }

    public void criteriaViewMonth() {
        Slot[] daysm = MonthView.DefaultView(cur,1,1);
        if(!comboBox.getValue().equals("Default view")) {
            clearMonth(daysm);
            int start;
            start = MonthView.countDay(daysm);
            for (int i = 1; i < 84; i=i+2) {
                if(i<start*2){
                    eventListM.get(i - 1).setText(" ");
                    eventListM.get(i).setText(" ");
                }
                else if (((i-1)/2-start)<daysm[1].getDate().lengthOfMonth()){
                    List<Event> printEvents;
                    printEvents = criteria.typeCriteria(daysm[(i-1)/2-start].getTodayEvents(), getCategory());
                    if (printEvents.size()==1) { //if the array of events for this day is not empty
                        try {
                            eventListM.get(i - 1).setText(printEvents.get(0).getName());
                        } catch (IndexOutOfBoundsException e) {
                            eventListM.get(i - 1).setText(" ");
                            eventListM.get(i).setText(" ");
                        }
                    }
                    else if (printEvents.size()>1) { //if the array of events for this day is not empty
                        try {
                            eventListM.get(i - 1).setText(printEvents.get(0).getName());
                            eventListM.get(i).setText(printEvents.get(1).getName());
                        } catch (IndexOutOfBoundsException e) {
                            eventListM.get(i - 1).setText(" ");
                            eventListM.get(i).setText(" ");
                        }
                    }
                    else{
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    }
                }
                else{
                    eventListM.get(i - 1).setText(" ");
                    eventListM.get(i).setText(" ");
                }
            }
        }
        else fillEventsMonth(daysm);
    } //LA

    public void criteriaViewWeek() {
        Slot[] daysw = WeekView.DefaultView(curw, 1, 1);
        if(!comboBox.getValue().equals("Default view")) {
            clearWeek(daysw);
            for (int i = 1; i <= 7; i++) {
                List<Event> printEvents = criteria.typeCriteria(daysw[i - 1].getTodayEvents(), getCategory());
                for (int j = 1; j <= 7; j++) {
                    if (!printEvents.isEmpty()) {
                        try {
                            eventListW.get(7 * (i - 1) + j - 1).setText(printEvents.get(j - 1).getName());
                        } catch (IndexOutOfBoundsException e) {
                            eventListW.get(7 * (i - 1) + j - 1).setText(" ");
                        }
                    } else {
                        eventListW.get(7 * (i - 1) + j - 1).setText(" ");
                    }
                }
            }
        }
        else fillEventsWeek(daysw);

    } //LA

    public void criteriaViewDay() {
        Slot day = DayView.DefaultView(cur,1,1);
        if(!comboBox.getValue().equals("Default view")){
            clearDay(day);
            List<Event> printEvents = criteria.typeCriteria(day.getTodayEvents(), getCategory());
            for (int i = 0; i < 8; i++) {
                if ((printEvents.size()>=1)) {
                    try {
                        evListD.get(i).setText(printEvents.get(i).getName());
                        if(printEvents.get(i).getDescription()!=null){
                            descrList.get(i).setText(printEvents.get(i).getDescription());
                        }
                        else{
                            descrList.get(i).setText("-");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        evListD.get(i).setText(" ");
                        descrList.get(i).setText(" ");
                    }
                }
                else{
                    evListD.get(i).setText(" ");
                    descrList.get(i).setText(" ");
                }
            }
        }
        else fillEventsDay(day);
    } //LA

    public void clearMonth(Slot[] days) {
        int start;
        start = MonthView.countDay(days);
        for (int i = 1; i < 84; i=i+2) {
            if(i<start*2){
                eventListM.get(i - 1).setText(" ");
                eventListM.get(i).setText(" ");
            }
            else if (((i-1)/2-start)<days[1].getDate().lengthOfMonth()){
                List<Event> printEvents;
                printEvents = days[(i-1)/2-start].getTodayEvents();
                if (printEvents.size()==1) { //if the array of events for this day is not empty
                    try {
                        eventListM.get(i - 1).setText(" ");
                    } catch (IndexOutOfBoundsException e) {
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    }
                }
                else if (printEvents.size()>1) { //if the array of events for this day is not empty
                    try {
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    } catch (IndexOutOfBoundsException e) {
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    }
                }
                else{
                    eventListM.get(i - 1).setText(" ");
                    eventListM.get(i).setText(" ");
                }
            }
            else{
                eventListM.get(i - 1).setText(" ");
                eventListM.get(i).setText(" ");
            }
        }

    } //LA

    public void clearWeek(Slot[] days) {
        for (int i = 1; i <= 7; i++) {
            List<Event> printEvents = days[i - 1].getTodayEvents();
            for (int j = 1; j <= 7; j++) {
                if (!printEvents.isEmpty()) {
                    try {
                        eventListW.get(7*(i-1)+j-1).setText(" ");
                    } catch (IndexOutOfBoundsException e) {
                        eventListW.get(7*(i-1)+j-1).setText(" ");
                    }
                }
                else {
                    eventListW.get(7*(i-1)+j-1).setText(" ");
                }
            }
        }


    } //LA

    public  void clearDay(Slot day) {
        for (int i = 0; i < 8; i++) {
            List<Event> printEvents = day.getTodayEvents();
            if ((printEvents.size()>=1)) {
                try {
                    evListD.get(i).setText(" ");
                    if(printEvents.get(i).getDescription()!=null){
                        descrList.get(i).setText(" ");
                    }
                    else{
                        descrList.get(i).setText("-");
                    }
                } catch (IndexOutOfBoundsException e) {
                    evListD.get(i).setText(" ");
                    descrList.get(i).setText(" ");
                }
            }
            else{
                evListD.get(i).setText(" ");
                descrList.get(i).setText(" ");
            }
        }

    } //LA

    public String getCategory(){
        return comboBox.getValue();
    } //LA

    public void viewButton() {
        if(viewWeek.isSelected()) {
            criteriaViewWeek();
        }
        else if (viewMonth.isSelected()) {
            criteriaViewMonth();
        }
        else {
            criteriaViewDay();
        }

    } //LA

    public void click(MouseEvent mouseEvent) {
        String str = mouseEvent.getSource().toString().replace("Text[id=", "");
        String eventName1 = "No name";
        char[] chars = new char[3];
        str.getChars(0, 3, chars, 0);
        str = Arrays.toString(chars);
        str = str.replace("[", "").replace(",", "")
                .replace("]", "").replace(" ", "");
        for (Text text: eventListM) {
            if(text.getId().equals(str)) {
                eventName1 = text.getText();
                System.out.println(eventName1);
                break;
            }
        }
        ControllerDeleteEdit.getName(eventName1);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("version3.2_OB.fxml"));
        Scene secondScene = null;
        try {
            secondScene = new Scene(fxmlLoader.load(), 380, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newWindow = new Stage();
        newWindow.setTitle("Event info");
        newWindow.setScene(secondScene);
        newWindow.show();
    }

    public static void getName(String name1) {
        name = name1;
        isEdited = true;
    }
    public void edit1() {
        Slot[] days = MonthView.DefaultView(cur,1,1);
        int start;
        start = MonthView.countDay(days);
        for (int i = 1; i < 84; i=i+2) {
            if(i<start*2){
                eventListM.get(i - 1).setText(" ");
                eventListM.get(i).setText(" ");
            }
            else if (((i-1)/2-start)<days[1].getDate().lengthOfMonth()){
                List<Event> printEvents;
                printEvents = days[(i-1)/2-start].getTodayEvents();
                if (printEvents.size()==1) { //if the array of events for this day is not empty
                    try {
                        eventListM.get(i - 1).setText(printEvents.get(0).getName());
                    } catch (IndexOutOfBoundsException e) {
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    }
                }
                else if (printEvents.size()>1) { //if the array of events for this day is not empty
                    try {
                        eventListM.get(i - 1).setText(printEvents.get(0).getName());
                        eventListM.get(i).setText(printEvents.get(1).getName());
                    } catch (IndexOutOfBoundsException e) {
                        eventListM.get(i - 1).setText(" ");
                        eventListM.get(i).setText(" ");
                    }
                }
                else{
                    eventListM.get(i - 1).setText(" ");
                    eventListM.get(i).setText(" ");
                }
            }
            else{
                eventListM.get(i - 1).setText(" ");
                eventListM.get(i).setText(" ");
            }
        }
    }
}