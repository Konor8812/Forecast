package com.illia.forecast.core.api.openai.response;

import java.util.List;

public record MessageResponse(String role,
                              String content,
                              List<FunctionCall> tool_calls) {

}
