package com.illia.forecast.core.api.openai.response;

import java.util.Map;

public record Function(String name,
                       Map<String, String> arguments) {

}
