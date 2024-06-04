package com.illia.telegram.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import coresearch.cvurl.io.mapper.MapperFactory;
import coresearch.cvurl.io.model.CVurlConfig;
import coresearch.cvurl.io.request.CVurl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CVurlClientConfig {
  @Bean
  public CVurl cVurl(ObjectMapper objectMapper) {
    var cvurlConfig = CVurlConfig.builder()
        .genericMapper(MapperFactory.from(objectMapper))
        .build();
    return new CVurl(cvurlConfig);
  }
}
