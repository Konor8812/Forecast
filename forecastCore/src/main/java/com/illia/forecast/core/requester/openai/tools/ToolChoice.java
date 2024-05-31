package com.illia.forecast.core.requester.openai.tools;

public record ToolChoice(String type,
                         ToolChoiceFunction function
) {

  public ToolChoice(String toolChoice) {
    this("function", new ToolChoiceFunction(toolChoice));
  }

  private record ToolChoiceFunction(String name) {

  }
}
