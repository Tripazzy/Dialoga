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
            String topLine = reader.nextLine();
            if (!topLine.startsWith("new")) break;

            final long id = Long.valueOf(topLine.split("-")[1]);
            Person person = new Person();

            String line = reader.nextLine();
            while (!line.equals("")) {
                String[] property = line.split(":");
                person.setProperty(property[0], property[1]);
                line = reader.hasNextLine() ? reader.nextLine() : "";
            }
            people.put(id, person);
        }
    }

    private Map<String, String> properties = new HashMap<>();

    public String getProperty(String property) {
        return properties.get(property);
    }

    public void setProperty(String property, String value) {
        properties.put(property, value);
    }
}
