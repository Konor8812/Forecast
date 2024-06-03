package com.illia.telegram.bot.service.processor.registry;

/**
 *  Contains enumeration for all possible commands format /<command>
 *  e. g. /start
 */

public enum BotCommandsRegistry {
    START("/start", "startMessageTextProcessor");

    private final String command;
    private final String processorName;

    BotCommandsRegistry(String command, String processorName){
        this.command = command;
        this.processorName = processorName;
    }

    public String getCommand() {
        return command;
    }

    public String getProcessorName() {
        return processorName;
    }
}
