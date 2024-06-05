package com.illia.telegram.bot.config;

import com.illia.telegram.bot.service.TelegramService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig  {

//  private final TelegramClientConfig telegramClientConfig;
//  private final TelegramService service;
//
//  @Override
//  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//    taskRegistrar.addFixedRateTask(service::getUpdates, Duration.ofMillis(telegramClientConfig.getRefreshRate()));
//  }
}
