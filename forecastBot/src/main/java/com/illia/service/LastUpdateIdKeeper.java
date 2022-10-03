package com.illia.service;

public interface LastUpdateIdKeeper {
    long getUpdateId();

    void setUpdateId(long lastUpdateId);
}
