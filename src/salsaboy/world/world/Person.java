package salsaboy.world.world;

import net.dv8tion.jda.core.entities.Member;
import salsaboy.world.MainRunner;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Person {
    public static Map<Long, Person> people = new HashMap<>();
    static {
        for (Member user : MainRunner.dialogaServer.getMembers()) {
            Person toAdd = new Person();
            people.put(user.getUser().getIdLong(), toAdd);
        }
        Scanner reader = new Scanner(Person.class.getResourceAsStream("people.txt"));   //LMAO Github nubs
        while (reader.hasNextLine()) {
            final long name = Long.valueOf(reader.nextLine().split(":")[1]);
            people.get(name).dialos = reader.nextFloat();
        }
    }
    
    public float dialos;
}
