package com.illia.telegram.bot.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class LastUpdateIdKeeperImpl implements LastUpdateIdKeeper{

    private final AtomicLong lastUpdateId = new AtomicLong();

    @Override
    public AtomicLong getUpdateId() {
        return lastUpdateId;
    }

    @Override
    public void setUpdateId(long lastUpdateId) {
        this.lastUpdateId.set(Math.max(lastUpdateId, this.lastUpdateId.get()));
    }
}
