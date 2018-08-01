package salsaboy.world;

import java.util.ArrayList;
import java.util.Objects;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import salsaboy.world.world.Person;
import javax.security.auth.login.LoginException;

public class MainRunner implements EventListener {
    public static final boolean MAY_INVITE = false;

    //For people who can administer the bot, of course
    private static final ArrayList<Long> administrators = new ArrayList<>();
    static {
        administrators.add(363537161137422337L); // Salsa Boy
        administrators.add(290324741187698688L); // TheOnlyMrCat
    }

    public static JDA jda;
    public static Guild dialogaServer;
    public static void main(String[] args) throws LoginException, InterruptedException {
        // Note: It is important to register your ReadyListener before building
        jda = new JDABuilder(AccountType.BOT)
            .setGame(Game.playing("v0.0"))
            .setToken(args[0])
            .addEventListener(new MainRunner())
            .buildBlocking();

        dialogaServer = jda.getGuildById(471397977613926400L);
    }
    
    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            System.out.println("API is ready!");
        } else if (event instanceof GuildMessageReceivedEvent) {
            RunCommands.run(event, ((GuildMessageReceivedEvent) event).getAuthor().getName(),
                    ((GuildMessageReceivedEvent) event).getMessage().getContentRaw());
        } else if (event instanceof PrivateMessageReceivedEvent) {
            if (administrators.contains(((PrivateMessageReceivedEvent) event).getMessage().getAuthor().getIdLong())) {
                String[] msg = ((PrivateMessageReceivedEvent) event).getMessage().getContentRaw().split(" ");
                String channelId = msg[0];
                MessageChannel channel = dialogaServer.getTextChannelById(channelId);
                if (Objects.equals(channel, null)) {
                    ((PrivateMessageReceivedEvent) event).getChannel().sendMessage("I can't find that channel on the Dialoga server.").queue();
                } else {
                    MessageBuilder builder = new MessageBuilder(((PrivateMessageReceivedEvent) event).getMessage());
                    builder.replaceFirst(channelId, "");
                    channel.sendMessage(builder.build()).queue();
                }
            } else {
                ((PrivateMessageReceivedEvent) event).getChannel().sendMessage("Sorry, I can only talk to you on the Dialoga server.").queue();
            }
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
