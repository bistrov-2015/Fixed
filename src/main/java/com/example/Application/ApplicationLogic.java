package com.example.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLogic {

    ApplicationInterface applicationInterface;

    @Autowired
    public ApplicationLogic(ApplicationInterface applicationInterface) { // Если нужно инжектить другой класс - применяют
                                                                         // @Qualifier(String value) у сигнатуры метода
        this.applicationInterface = applicationInterface;
    }

    public ApplicationLogic() {
    }


    public void runApplication() {
            /*
            *запуск консольного приложения SimpleConsoleApplication реализованного только средствами языка Java. Задание_1
            * Классы относящиеся только к SimpleConsoleApplication :
            * SimpleConsoleApplication; SimpleConsoleApplicationIOMethods
            */
//            SimpleConsoleApplication simpleConsoleApplication = new SimpleConsoleApplication();
//            simpleConsoleApplication.runApplication();
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "ApplicationContext.xml"
            );
            SpringConsoleApplication springConsoleApplication = context.getBean(SpringConsoleApplication.class);
            springConsoleApplication.runApplication();

    }
}
