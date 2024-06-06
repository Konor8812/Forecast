package com.illia.forecast.core.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class OpenAIConfig {

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    @Value("${openai.completions.url}")
    private String openaiCompletionsUrl;

    @Value("${openai.model-name}")
    private String modelName;

}