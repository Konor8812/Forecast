package com.illia.forecast.core.api.openai.request;

import java.util.ArrayList;
import java.util.List;

public record OpenAIRequestBody(String model, List<Message> messages) {

    public OpenAIRequestBody(String model) {
        this(model, new ArrayList<>());
    }

    public void addMessage(String role, String content) {
        this.messages.add(new Message(role, content));
    }

}
