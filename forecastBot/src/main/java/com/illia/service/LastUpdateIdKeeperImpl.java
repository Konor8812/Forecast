package com.illia.service;

import org.springframework.stereotype.Component;

@Component
public class LastUpdateIdKeeperImpl implements LastUpdateIdKeeper{

    long lastUpdateId;

    @Override
    public long getUpdateId() {
        return lastUpdateId;
    }

    @Override
    public void setUpdateId(long lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }
}
