package com.example.Application;


import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommunicationWithTheUser {

    public void showMenu(){
        System.out.print("Просмотреть словарь - нажмите 1; " +
                "Найти запись - нажмите 2; " +
                "Добавить запись - нажмите 3; " +
                "Удалить запись - нажмите 4; " +
                "Завершить работу программы - нажмите 5;" +"\n");
    }

    public void reportLanguageDictionaryFileCreation(){
        System.out.println("Файл LanguageDictionary.txt не был найден на диске, создан новый файл.");
    }

    public void reportLanguageDictionaryFileCreationFailed(){
        System.out.println("Не удалось создать файл LanguageDictionary.txtпо пути (MyApplication/public class CheckAndRequestFunctions/checkFileExistence)" );
    }

    public void reportNumericDictionaryFileCreation(){
        System.out.println("Файл NumericDictionary.txt не был найден, создан новый файл.");
    }

    public void reportNumericDictionaryFileCreationFailed(){
        System.out.println("Не удалось создать файл NumericDictionary.txtпо пути (MyApplication/public class CheckAndRequestFunctions/checkFileExistence)" );
    }

    public void promptDictionary(){
        System.out.print("Нажмите 1, чтобы выбрать Language Dictionary или 2, чтобы выбрать Numeric Dictionary " + "\n" + "<<");
    }

    public void showStringFormatForExpression(){
        System.out.println("Введидите слово из 4 букв на латинской раскладке");
    }

    public void showNumberFormatForExpression(){
        System.out.println("Введидите число из 5 цифр");
    }

    public void showStringFormatForExpressionValue(){
        System.out.println("Введидите слово на русском языке");
    }
    public void showNumberFormatForExpressionValue(){
        System.out.println("Введидите число из 5 цифр");
    }

    public String promptLine(){
        String line;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введидите слово<< ");
        line = scanner.nextLine();
        return line;
    }

    public void promptValue(){
        System.out.println("Введите значение");
    }

    public void promptAction(){
        System.out.print("Выберите действие << ");
    }

    public void stringNotFound(){
        System.out.println("Поиск не дал результатов");
    }

    /*
    Методы для Exeption
     */
    public void fileReadError(){
        System.out.println("Ошибка чтения файла (MyApplication/public class SimpleConsoleApplicationIOMethods/ ) IOException");
    }

    public void fileWritingError(){
        System.out.println("Ошибка записи в файл (MyApplication/public class SimpleConsoleApplicationIOMethods/ ) IOException");
    }
}
