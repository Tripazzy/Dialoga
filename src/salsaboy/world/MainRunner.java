package salsaboy.world;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import salsaboy.world.world.Person;
import javax.security.auth.login.LoginException;

public class MainRunner implements EventListener {
    public static final boolean MAY_INVITE = false;
    
    public static JDA jda;
    public static void main(String[] args) throws LoginException, InterruptedException {
        // Note: It is important to register your ReadyListener before building
        jda = new JDABuilder(AccountType.BOT)
            .setGame(Game.playing("v0.0"))
            .setToken(args[0])
            .addEventListener(new MainRunner())
            .buildBlocking();
    }
    
    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            System.out.println("API is ready!");
        } else if (event instanceof MessageReceivedEvent) {
            RunCommands.run(event, ((MessageReceivedEvent) event).getAuthor().getName(),
                ((MessageReceivedEvent) event).getMessage().getContentRaw());
        } else if (event instanceof GuildMemberJoinEvent) {
            if (!MAY_INVITE) {
                if ((!((GuildMemberJoinEvent) event).getMember().getUser().getName().equals("VocalEcho")) &&
                    (!((GuildMemberJoinEvent) event).getMember().getUser().getName().equals("TheOnlyMrCat")) &&
                    (!((GuildMemberJoinEvent) event).getMember().getUser().getName().equals("taylor.gizelle"))) {
                    ((GuildMemberJoinEvent) event).getGuild().getController()
                        .kick(((GuildMemberJoinEvent) event).getMember()).queue();
                }
            } else {
                Person toMake = new Person();
                
                toMake.dialos = 1000.00f;
            }
        }
    }
}
