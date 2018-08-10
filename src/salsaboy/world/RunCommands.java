package salsaboy.world;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import salsaboy.world.world.Person;

public class RunCommands {
    public static void run(GuildMessageReceivedEvent event, String author, String message) {
        if (!message.startsWith("?")) return;
        if (message.equals("?hi")) {
            final int response = (int) (Math.random() * 5);
            switch (response) {
                case 0:
                    event.getChannel().sendMessage("Have you ever been fed to the fishies?").queue();
                    break;
                case 1:
                    event.getChannel().sendMessage("ur mom gay").queue();
                    break;
                case 2:
                    event.getChannel().sendMessage("Despacito").queue();
                    break;
                case 3:
                    event.getChannel().sendMessage(":joy: lol u gay").queue();
                    break;
				case 4:
					event.getChannel().sendMessage("Are you saying hello to a bot now?").queue();
					break;
            }
        } else if (message.startsWith("?suicide")) {
            switch (message.split(" ")[1]) {
                case "falling":
                    Person.people.remove(event.getAuthor().getIdLong());
                    event.getGuild().getController().kick(event.getMember()).queue();
                    break;
            }
        } else if (message.startsWith("?balance")){
            String term = message.split(" ")[1];
            if (term.equals("diolas")) {
                event.getChannel().sendMessage(
                        String.format("%s has ∂%s",
                                event.getAuthor().getName(),
                                Person.people.get(event.getAuthor().getIdLong()).getProperty("diolas"))
                ).queue();
            } else if (term.equals("all")) {
                //Show everything
            }
        }
    }
}
