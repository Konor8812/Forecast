package com.illia.forecast.core.api.openai.response;

import java.util.List;

public record OpenAIMatchingResponse(List<Choice> choices) {

}
