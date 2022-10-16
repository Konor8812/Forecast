package com.illia.telegram.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GetUpdatesRequest {
    @JsonProperty("offset")
    long offset;
}
