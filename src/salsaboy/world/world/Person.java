package salsaboy.world.world;

import net.dv8tion.jda.core.entities.User;
import salsaboy.world.MainRunner;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Person {
    public static Map<String, Person> people = new HashMap<>();
    static {
        for (User user : MainRunner.jda.getUsers()) {
            Person toAdd = new Person();
            people.put(user.getName(), toAdd);
        }
        Scanner reader = new Scanner(Person.class.getResource("people.txt").toString());   //LMAO Github nubs
        while (reader.hasNext()) {
            final String name = reader.nextLine().split(":")[1];
            people.get(name).dialos = reader.nextFloat();
        }
    }
    
    public float dialos;
}
