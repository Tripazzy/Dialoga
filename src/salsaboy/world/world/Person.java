package salsaboy.world.world;

import net.dv8tion.jda.core.entities.User;
import salsaboy.world.MainRunner;
import java.util.HashMap;
import java.util.Map;

public class Person {
    public static Map<String, Person> people = new HashMap<>();
    static {
        for (User user : MainRunner.jda.getUsers()) {
            Person toAdd = new Person();
            people.put(user.getName(), toAdd);
        }
    }
    
    public float dialos;
}
