package com.illia.forecast.core.api.openai.response;

public record FunctionCall(String id,
                           String type,
                           Function function) {
}
