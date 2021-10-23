package com.belolipetsckiy.spring.rest;

import com.belolipetsckiy.spring.rest.configuration.MyConfig;
import com.belolipetsckiy.spring.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUser = communication.getAllUsers();
        System.out.println(allUser);

        User user = new User(3, "James", "Brown",(byte) 30);
        String save = communication.saveUser(user);
        User user1 = new User(3, "Thomas", "Shelby",(byte) 30);
        String update = communication.updateUser(user1);
        String delete = communication.deleteUser(3);
        System.out.println(save + update + delete);


    }
}
