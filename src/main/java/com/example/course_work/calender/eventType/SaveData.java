package com.example.course_work.calender.eventType;

import com.example.course_work.calender.eventCategory.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;
    String filename1 = "event1.dat";
    String filename2 = "event2.dat";
    String filename3 = "event3.dat";

    public Event enterDataEvent(){
        Scanner in = new Scanner(System.in);
        Event event = new Event();
        String name = in.nextLine();
        event.setName(name);
        int year = in.nextInt(),
                month = in.nextInt(),
                day = in.nextInt();
        LocalDate date = LocalDate.of(year, month, day);
        event.setDate(date);
        String forCorrectWork = in.nextLine();
        String categoryName = in.nextLine();
        Category cat = new Category(categoryName);
        event.setCategory(cat);
        event.getCategory().choose(cat);
        String description1 = in.nextLine();
        event.setDescription(description1);
        //ввод названия ивента, категории; даты и описания с клавиатуры
       // in.close();
        return event;
    }
    public LongEvent enterDataLE() throws IOException {
        Event timed;
        timed = enterDataEvent();
        LongEvent currentLE = new LongEvent();
        currentLE.setName(timed.getName());
        currentLE.setDate(timed.getDate());
        currentLE.setCategory(timed.getCategory());
        currentLE.setDescription(timed.getDescription());
        Scanner in = new Scanner(System.in);
        String location;
        location = in.nextLine();
        currentLE.setLocation(location);
        //currentLE.timeSet.setTimeSet();
        return currentLE;
    }
    public Task enterDataTask() {
        Event timed = enterDataEvent();
        Task currentTask = new Task();
        currentTask.setName(timed.getName());
        currentTask.setDate(timed.getDate());
        currentTask.setCategory(timed.getCategory());
        currentTask.setDescription(timed.getDescription());
        //currentTask.setEndTime();
        return currentTask;
    }
    public void addEvent() throws IOException {
        Event currentEvent;
        currentEvent = enterDataEvent();
        currentEvent.add(currentEvent);
        FileOutputStream outputStream = new FileOutputStream(filename1);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(Event.getArrayListEvent());
        objectOutputStream.close();
        currentEvent.print();
    }

    public void addLE() throws IOException {
        LongEvent currentLE;
        currentLE = enterDataLE();
        currentLE.add();
        FileOutputStream outputStream = new FileOutputStream(filename2);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(currentLE.getArrayListLE());
        objectOutputStream.close();
    }

    public void addTask() throws IOException{
        Task currentTask = enterDataTask();
        currentTask.add();
        FileOutputStream outputStream = new FileOutputStream(filename3);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(currentTask.getArrayList1());
        objectOutputStream.close();
    }
    public void editEvent(int index, int choosen) throws IOException, ClassNotFoundException {
        switch (choosen) {
            case (1) :
                Event currentEvent = enterDataEvent();
                currentEvent.edit(index, currentEvent);
                FileOutputStream outputStream = new FileOutputStream(filename1);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(Event.getArrayListEvent());
                objectOutputStream.close();
            break;
            case (2) :
                LongEvent event1 = enterDataLE();
                event1.setTimeSetLE();
                event1.edit(index, event1);
                FileOutputStream outputStream1 = new FileOutputStream(filename2);
                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(outputStream1);
                objectOutputStream1.writeObject(event1.getArrayListLE());
                objectOutputStream1.close();
            break;
            case (3) :
                Task task = enterDataTask();
                task.edit(index, task);
                FileOutputStream outputStream2 = new FileOutputStream(filename3);
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(outputStream2);
                objectOutputStream2.writeObject(task.getArrayList1());
                objectOutputStream2.close();
            break;
        }
    }

}
