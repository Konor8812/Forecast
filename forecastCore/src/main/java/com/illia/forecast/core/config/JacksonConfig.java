package com.illia.forecast.core.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.illia.forecast.core.api.openai.response.Function;
import java.io.IOException;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public Module functionDeserializerModule() {
    var simpleModule = new SimpleModule();
    simpleModule.addDeserializer(Function.class, new FunctionDeserializer());
    return simpleModule;
  }

  public static class FunctionDeserializer extends JsonDeserializer<Function> {

    @Override
    public Function deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
      ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
      Map<String, Object> node = mapper.readValue(jsonParser, Map.class);

      String name = (String) node.get("name");
      String argumentsJson = (String) node.get("arguments");

      Map<String, String> arguments = mapper.readValue(argumentsJson, Map.class);

      return new Function(name, arguments);
    }
  }
}
