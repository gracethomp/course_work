package com.example.course_work.calender;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class CalenderConsoleMenu {

    public static final String[] options = { "1. Event  without time duration.",
            "2. Event with time duration. ",
            "3. Task.",
    };

    public static void mainMenuText(){
        System.out.println("*******CALENDER MENU SELECTION*******");
        System.out.println("*1. Create event.                   *");
        System.out.println("*2. Choose event.                   *");
        System.out.println("*3. Show default view               *");
        System.out.println("*4. Clear screen.                   *");
        System.out.println("*5. Exit.                           *");
        System.out.println("*************************************");
    }

    public static void printMainMenu() throws IOException {
        CalenderFacade a = new CalenderFacade();
        int option;
        do {
            mainMenuText();
            System.out.println("Choose option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            {
                switch (option) {
                    case 1:
                        a.createEvent();
                        break;
                    //case 2: a.selectEvent(); break;
                    case 3:
                        CalenderFacade.startView();
                        break;
                    case 4:
                        CalenderFacade.clearScreen();
                        break;
                    case 5:
                        exit(0);
                }

            }
        } while (option != 6);
    }

    public static void printMenu(String[] options){
        System.out.println("*******CALENDER MENU SELECTION******");
        System.out.println("Choose type of data you want to save: ");
        for (String option : options){
            System.out.println(option);
        }
    }

    public static void selectionMenu(int key) {
        try {
            switch (key){
                case 1:
                    System.out.println("Option 1 selected");
                    break;
                case 2:
                    System.out.println("Option 2 selected");
                    break;
                case 3:
                    System.out.println("Option 3 selected");
                    break;
                }
            }
        catch (Exception ex){
                System.out.println("Valid input. Please enter an integer value between 1 and " + options.length);
            }
        }
    }
