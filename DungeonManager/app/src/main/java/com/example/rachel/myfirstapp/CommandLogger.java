package com.example.rachel.myfirstapp;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by William Glanton on 4/15/2016.
 */
public class CommandLogger implements Serializable {
    private ArrayList<Command> commandLog;
    private static volatile CommandLogger singleton;

    private CommandLogger(){
        commandLog = new ArrayList<Command>();
    }

    public static CommandLogger getInstance(){
        if(singleton == null){
            synchronized(CommandLogger.class) {
                if(singleton == null) {
                    singleton = new CommandLogger();
                }
            }
        }
        return singleton;
    }

    public void logCommand(Command cmd){
        commandLog.add(cmd);
    }

    public String[] getLog(){
        if(commandLog.size() == 0){
            return null;
        }
        String log[] = new String[commandLog.size()];
        for(int i = 0; i < log.length; i++){
            log[i] = commandLog.get(i).getDescription();
        }
        return log;
    }
}
