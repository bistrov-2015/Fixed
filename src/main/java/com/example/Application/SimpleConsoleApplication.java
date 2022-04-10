package com.example.Application;

public class SimpleConsoleApplication implements ApplicationInterface {
    String selectedItem;
    SimpleConsoleApplicationIOMethods simpleConsoleApplicationIOMethods = new SimpleConsoleApplicationIOMethods();
    CheckAndRequestFunctions checkAndRequestFunctions;
    CommunicationWithTheUser communicationWithTheUser;
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
            simpleConsoleApplicationIOMethods.showDictionary();
        } else if(selectedItem.equals("2")){
            simpleConsoleApplicationIOMethods.findEntryInDictionary();
        } else if(selectedItem.equals("3")){
            simpleConsoleApplicationIOMethods.makeEntryInDictionary();
        } else if(selectedItem.equals("4")){
            simpleConsoleApplicationIOMethods.deleteEntryInDictionary();
        } else if (selectedItem.equals("5")){
            RUN = false;
        }
    }
}
