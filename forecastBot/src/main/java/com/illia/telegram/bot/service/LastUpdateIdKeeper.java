package com.illia.telegram.bot.service;

import java.util.concurrent.atomic.AtomicLong;

public interface LastUpdateIdKeeper {
    AtomicLong getUpdateId();

    void setUpdateId(long lastUpdateId);
}
