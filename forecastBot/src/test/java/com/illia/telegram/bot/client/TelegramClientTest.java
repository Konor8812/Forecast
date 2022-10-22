package com.illia.telegram.bot.client;

import com.illia.telegram.bot.config.ApplicationConfig;
import com.illia.telegram.bot.config.TelegramClientConfig;
import com.illia.telegram.bot.service.TelegramService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TelegramClientTest {

    @MockBean
    TelegramClientConfig tconf;
    @MockBean
    ApplicationConfig config;
    @MockBean
    TelegramService tservice;

    @Test
    public void telegramScheduledRequesterTest(){

        when(tconf.getRefreshRate()).thenReturn(1L);

        new ApplicationConfig.TelegramScheduler(tservice, tconf);
        try {
            Thread.sleep(1015);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(tservice, atLeast(10)).getUpdates();
    }

}
