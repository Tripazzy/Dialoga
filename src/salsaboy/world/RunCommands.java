package salsaboy.world;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import salsaboy.world.world.Person;

public class RunCommands {
    public static void run(Event event, String author, String message) {
        if (!message.startsWith("?")) return;
        if (message.equals("?hi")) {
            final int response = (int) (Math.random() * 4);
            switch (response) {
                case 0:
                    ((MessageReceivedEvent) event).getChannel().sendMessage("Have you ever been fed to the fishies?").queue();
                    break;
                case 1:
                    ((MessageReceivedEvent) event).getChannel().sendMessage("ur mom gay").queue();
                    break;
                case 2:
                    ((MessageReceivedEvent) event).getChannel().sendMessage("Despacito").queue();
                    break;
                case 3:
                    ((MessageReceivedEvent) event).getChannel().sendMessage(":joy: lol u gay").queue();
                    break;
            }
        } else if (message.startsWith("?suicide")) {
            switch (message.split(" ")[1]) {
                case "falling":
                    Person.people.remove(((MessageReceivedEvent) event).getAuthor().getName());
                    ((MessageReceivedEvent) event).getGuild().getController()
                        .kick(((MessageReceivedEvent) event).getMember()).queue();
                    break;
            }
        } else if (message.startsWith("?balance")){
            String term = message.split(" ")[1];
            if (term.equals("diolas")) {
                ((MessageReceivedEvent) event).getChannel().sendMessage(
                    ((MessageReceivedEvent) event).getAuthor() + " has ∂" +
                        Person.people.get(((MessageReceivedEvent) event).getAuthor().getName()).dialos
                ).queue();
            }

            else if (term.equals("all")) {
                //Show everything
            }
        }
    }
}
