package com.illia.telegram.bot.service;

public interface LastUpdateIdKeeper {
    long getUpdateId();

    void setUpdateId(long lastUpdateId);
}
