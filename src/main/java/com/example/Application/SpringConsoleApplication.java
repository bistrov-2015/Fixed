package com.example.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringConsoleApplication implements ApplicationInterface{
    String selectedItem;

    SpringConsoleApplicationIOMethods springConsoleApplicationIOMethods;
    CheckAndRequestFunctions checkAndRequestFunctions;
    CommunicationWithTheUser communicationWithTheUser;

    @Autowired
    public SpringConsoleApplication(SpringConsoleApplicationIOMethods springConsoleApplicationIOMethods, CheckAndRequestFunctions checkAndRequestFunctions, CommunicationWithTheUser communicationWithTheUser) {
        this.springConsoleApplicationIOMethods = springConsoleApplicationIOMethods;
        this.checkAndRequestFunctions = checkAndRequestFunctions;
        this.communicationWithTheUser = communicationWithTheUser;
    }

    boolean RUN = true;

    public void runApplication(){
        try {
            checkAndRequestFunctions.checkFileExistence();
            while (RUN) {
                showUserMenu();
                handleUserSelection();
                performSelectedAction();
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void showUserMenu(){
        communicationWithTheUser.showMenu();
    }

    public void  handleUserSelection(){
        selectedItem = checkAndRequestFunctions.promptUserSelection();
    }

    public void performSelectedAction(){
        if(selectedItem.equals("1")){
            springConsoleApplicationIOMethods.showDictionary();
        } else if(selectedItem.equals("2")){
            springConsoleApplicationIOMethods.findEntryInDictionary();
        } else if(selectedItem.equals("3")){
            springConsoleApplicationIOMethods.makeEntryInDictionary();
        } else if(selectedItem.equals("4")){
            springConsoleApplicationIOMethods.deleteEntryInDictionary();
        } else if (selectedItem.equals("5")){
            RUN = false;
        }
    }
}
