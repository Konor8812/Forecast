package com.illia.forecast.core.api.openai.request;

import com.illia.forecast.core.requester.openai.tools.Tool;
import com.illia.forecast.core.requester.openai.tools.ToolChoice;
import java.util.ArrayList;
import java.util.List;

public record OpenAIRequestBody(String model,
                                List<Message> messages,
                                List<Tool> tools,
                                ToolChoice tool_choice,
                                int max_tokens) {

  public OpenAIRequestBody(String model, String toolChoice, int max_tokens) {
    this(model, new ArrayList<>(), new ArrayList<>(), new ToolChoice(toolChoice), max_tokens);
  }

  public void addMessage(String role, String content) {
    this.messages.add(new Message(role, content));
  }

  public void addTool(Tool tool) {
    this.tools.add(tool);
  }
}
