package com.illia.telegram.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

public record GetUpdatesRequest(@JsonProperty("offset") long offset) {
}
