package com.illia.forecast.core.requester.openai.tools;

public record Parameters(String type,
                         Properties properties,
                         String[] required) {

}
