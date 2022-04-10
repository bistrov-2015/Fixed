package com.example.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

@Component
public class SpringConsoleApplicationIOMethods implements DictionaryInterface{

    CheckAndRequestFunctions checkAndRequestFunctions;
    CommunicationWithTheUser communicationWithTheUser;

    @Autowired
    public SpringConsoleApplicationIOMethods(CheckAndRequestFunctions checkAndRequestFunctions, CommunicationWithTheUser communicationWithTheUser) {
        this.checkAndRequestFunctions = checkAndRequestFunctions;
        this.communicationWithTheUser = communicationWithTheUser;
    }

    public void showDictionary(){
        BufferedReader br = null;
        String dictionariType = checkAndRequestFunctions.promptDictionaryType();
        try {
            br = new BufferedReader(new FileReader(defineDictionaryType(dictionariType)));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            communicationWithTheUser.fileReadError();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File defineDictionaryType(String dictionaryType){// переменнjq fileType присваивается значение взависимости от того каой словарь выбран
        File fileType = checkAndRequestFunctions.file1;
        if("1".equals(dictionaryType)){
            fileType = checkAndRequestFunctions.file1;
            return fileType;
        } else if("2".equals(dictionaryType)){
            fileType = checkAndRequestFunctions.file2;
            return fileType;
        }
        return fileType;// возможно создать дефолтный вайл
    }

    public Path definePathtoFile(String numDict){// переменной path присваивается значение взависимости от того каой словарь выбран
        Path path = checkAndRequestFunctions.path1;
        if("1".equals(numDict)){
            path = checkAndRequestFunctions.path1;
            return path;
        } else if("2".equals(numDict)){
            path = checkAndRequestFunctions.path2;
            return path;
        }
        return path;// возможно создать путь к дефолтному вайлу
    }

    public void  findEntryInDictionary(){
        String dictionariType = checkAndRequestFunctions.promptDictionaryType();
        File fileType = defineDictionaryType(dictionariType);
        BufferedReader br = null;
        String searchString = communicationWithTheUser.promptLine();
        String searchStringResult = null;

        try {
            br = new BufferedReader(new FileReader(fileType));
            String line;
            while ((line = br.readLine()) != null ) {
                if( line.contains(searchString)){
                    searchStringResult = line; break;
                }
            } br.close();
            if(searchStringResult != null){
                System.out.println(searchStringResult);
            } else communicationWithTheUser.stringNotFound();
        } catch (IOException e) {
            communicationWithTheUser.fileReadError();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void  makeEntryInDictionary(){
        String numDict = checkAndRequestFunctions.promptDictionaryType();
        Path pathToFile = definePathtoFile(numDict);
        String expression = checkAndRequestFunctions.requestExpressiont(numDict);
        communicationWithTheUser.promptValue();
        String expressionValue = checkAndRequestFunctions.requestExpressionValue(numDict);
        String checkedString = expression + "\t" + expressionValue;
        try {
//            Files.writeString(pathToFile, "\n" + checkedString, StandardOpenOption.APPEND);
            Files.write(pathToFile, Collections.singleton("\n" + checkedString), StandardOpenOption.APPEND);
        } catch (IOException e) {
            communicationWithTheUser.fileWritingError();
        }
    }

    public boolean chekRowExistensBeforeDeleting(String searchString, File fileType) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileType));
            String line;
            while ((line = br.readLine()) != null ) {
                if (line.startsWith(searchString) == true) {
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            communicationWithTheUser.fileReadError();

        }
        return false;
    }

    public void  deleteEntryInDictionary(){
        String dictionaryType = checkAndRequestFunctions.promptDictionaryType();
        File fileType = defineDictionaryType(dictionaryType);
        Path path = definePathtoFile(dictionaryType);
        File temporaryFile = new File("C:" + checkAndRequestFunctions.separator + "temp.txt");
        BufferedReader br = null;
        //communicationWithTheUser.promptLine();
        String searchString = checkAndRequestFunctions.requestExpressiont(dictionaryType);

        if(chekRowExistensBeforeDeleting(searchString,fileType) == true) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(temporaryFile));
                br = new BufferedReader(new FileReader(fileType));
                String line;

                while ((line = br.readLine()) != null) {
                    if (!line.contains(searchString)) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
                br.close();
                bw.close();
                if (Files.deleteIfExists(path)) {
                    System.out.println("delete");
                } else System.out.println("not delete");
                if (temporaryFile.renameTo(fileType)) {
                    System.out.println("successfully");
                } else System.out.println("not rename");


            } catch (IOException e) {
                communicationWithTheUser.fileReadError();

            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else System.out.println("нет такой строки");
    }
}
