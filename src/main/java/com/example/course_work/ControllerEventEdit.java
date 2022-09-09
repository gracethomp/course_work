package com.example.course_work;

import com.example.course_work.calender.eventCategory.Category;
import com.example.course_work.calender.eventType.Event;
import com.example.course_work.calender.eventType.LongEvent;
import com.example.course_work.calender.eventType.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ControllerEventEdit {
    private static final String REGEX_TIME = "^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$";
    Event event = new Event();
    LongEvent longEvent = new LongEvent();
    Task task = new Task();
    Category category = new Category();
    ObservableList<String> filtersList  = FXCollections.observableArrayList("Study", "Health", "Rest");

    @FXML
    private DatePicker date;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField location1;
    @FXML
    private DatePicker dateLong;
    @FXML
    private TextField nameLong;
    @FXML
    private TextField descriptionLong;
    @FXML
    private TextField endTime;
    @FXML
    private DatePicker dateReminder;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> comboBox;

    public ControllerEventEdit() throws IOException {
    }

    public void setName() {
        event.setName(getName());
    }

    public void setDate() {
        event.setDate(getDate());
    }

    public void setDescription() {
        event.setDescription(getDescription());
    }

    public String getName() {
        return name.getText();
    }

    public LocalDate getDate() {
        if(date != null)
            return date.getValue();
        return LocalDate.now();
    }
    public String getDescription() {
        return description.getText();
    }

    public Event getEvent() {
        return event;
    }

    public String getCategory(){
        return comboBox.getValue();
    } //LA

    public void initialize() {
        comboBox.setPromptText("Add filter");
        comboBox.setEditable(true);
        comboBox.setItems(filtersList);
        //comboBox.setOnAction( e -> category.setCategoryName(comboBox.getValue()));
    }

    public void setCategory() {
        category.setCategoryName(getCategory());
        category.add(category);
        longEvent.setCategory(category);
    }

    public void saveEvent() {
        if(date.getValue() != null) {
            event.add(event);
            event.deleteEvent(event.findByName(ControllerDeleteEdit.getName1()));
        }
    }

    public void setDateLong() {
        longEvent.setDate(dateLong.getValue());
    }

    public void setLocationLong() {
        longEvent.setLocation(location1.getText());
    }

    public void setNameLong() {
        longEvent.setName(nameLong.getText());
    }

    public void setDescriptionLong() {
        longEvent.setDescription(descriptionLong.getText());
        System.out.println(longEvent);
    }

    public void saveEventLong() {
        if(dateLong.getValue() != null) {
            longEvent.add(longEvent);
            System.out.println(longEvent);
        }
    }

    public void setDateReminder() {
        task.setDate(dateReminder.getValue());
    }

    public void setEndTime() {
        String[] subString = endTime.getText().split(":");
        System.out.println(Arrays.toString(subString));
        if(subString.length == 2)
            task.setEndTime(Integer.parseInt(subString[0]), Integer.parseInt(subString[1]));
    }

    public void saveTask() {
        if(Pattern.matches(REGEX_TIME, endTime.getText())) {
            task.add(task);
            System.out.println(task);

        } else {
            System.out.println(endTime.getText());
            label.setText("Format: XX:XX");
        }
    }
}
