package com.example.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class CheckAndRequestFunctions {
    String separator = File.separator;
    String numDict;
    String selectItem;

    CommunicationWithTheUser communicationWithTheUser;

    @Autowired
    public CheckAndRequestFunctions(CommunicationWithTheUser communicationWithTheUser) {
        this.communicationWithTheUser = communicationWithTheUser;
    }

    //    CommunicationWithTheUser communicationWithTheUser = new CommunicationWithTheUser();
    Path path1 = Paths.get("C:" + separator + "LanguageDictionary.txt");
    Path path2 = Paths.get("C:" + separator + "NumericDictionary.txt");
    File file1 = new File("C:" + separator + "LanguageDictionary.txt");
    File file2 = new File("C:" + separator + "NumericDictionary.txt");
    public void checkFileExistence() {
            try {
                File file1 = new File(String.valueOf(path1));
                if (file1.createNewFile())
                    communicationWithTheUser.reportLanguageDictionaryFileCreation();
            } catch (IOException e) {
                communicationWithTheUser.reportLanguageDictionaryFileCreationFailed();
            }
            try {
                File file2 = new File(String.valueOf(path2));
                if (file2.createNewFile())
                    communicationWithTheUser.reportNumericDictionaryFileCreation();

            } catch (IOException e) {
                communicationWithTheUser.reportNumericDictionaryFileCreationFailed();
            }
    }

    String promptUserSelection(){
        Scanner scanner = new Scanner(System.in);
        communicationWithTheUser.promptAction();
        selectItem = scanner.nextLine();
        if (chekUserSelection(selectItem) != true) {
            promptUserSelection();
        }
        return selectItem;
    }

    public boolean chekUserSelection(String userSelection){
        String regex = "[12345]";
        if (Pattern.matches(regex, userSelection) == true){
            return true;
        }else return false;
    }

    public String promptDictionaryType(){
        Scanner scanner = new Scanner(System.in);
        communicationWithTheUser.promptDictionary();
        numDict = scanner.nextLine();
        if (checkDictionaryTypeSelection(numDict) != true) {
            promptDictionaryType();
        }
        return numDict;
    }

    public boolean checkDictionaryTypeSelection(String Dict){
        String regex = "[12]";
        if (Pattern.matches(regex, Dict) == true){
            return true;
        } else return false;
    }



    public String requestExpressiont(String numDict){
        String stringToFile = communicationWithTheUser.promptLine();
        if("1".equals(numDict)){
            while (checkSymbolString(stringToFile) != true){
                communicationWithTheUser.showStringFormatForExpression();
                stringToFile = communicationWithTheUser.promptLine();
            }
        }else if ("2".equals(numDict)){
            while (checkNumericString(stringToFile) != true){
                communicationWithTheUser.showNumberFormatForExpression();
                stringToFile = communicationWithTheUser.promptLine();
            }
        }
        return stringToFile;
    }

    public String requestExpressionValue(String numDict){
        String stringToFile = communicationWithTheUser.promptLine();
        if("1".equals(numDict)){
            while (checkSymbolExpressionValue(stringToFile) != true){
                communicationWithTheUser.showStringFormatForExpressionValue();
                stringToFile = communicationWithTheUser.promptLine();
            }
        }else if ("2".equals(numDict)){
            while (checkNumericExpressionValue(stringToFile) != true){
                communicationWithTheUser.showNumberFormatForExpressionValue();
                stringToFile = communicationWithTheUser.promptLine();
            }
        }
        return stringToFile;
    }

    public boolean checkNumericString(String chekedStr) {
        if (chekedStr.matches("^[0-9]+$") == true & chekedStr.length() == 5 ) {
            return true;
        }
        return false;
    }

    public boolean checkSymbolString(String chekedStr) {
        if (chekedStr.matches("^[a-z]+$") == true & chekedStr.length() == 4 ) {// A-Z убрал чтоб был чувствителен к регистру
            return true;
        }
        return false;
    }

    public boolean checkSymbolExpressionValue(String chekedStr) {
        if (chekedStr.matches("^[а-яА-Я]+$") == true ) {
            return true;
        }
        return false;
    }

    public boolean checkNumericExpressionValue(String chekedStr) {
        if (chekedStr.matches("^[0-9]+$") == true ) {
            return true;
        }
        return false;
    }
}
