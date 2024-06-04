//package com.illia.telegram.bot.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.illia.telegram.bot.client.TelegramClient;
//import com.illia.telegram.bot.config.TelegramPollingScheduler;
//import com.illia.telegram.bot.config.TelegramClientConfig;
//import com.illia.telegram.bot.model.GetUpdatesResponse;
//import com.illia.telegram.bot.service.processor.GeneralMessageTextProcessor;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import reactor.core.publisher.Mono;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TelegramServiceTest {
//
//    @MockBean
//    TelegramClient client;
//    @MockBean
//    TelegramClientConfig tconf;
//    @MockBean
//    TelegramPollingScheduler config;
//    @MockBean
//    GeneralMessageTextProcessor generalMessageTextProcessor;
//    @MockBean
//    LastUpdateIdKeeper idKeeper;
//
//
//    @Test
//    public void testGetUpdates() {
//        TelegramServiceImpl telegramService = new TelegramServiceImpl(client, generalMessageTextProcessor, idKeeper);
//
//        int messagesAmount = 5;
//
//        List<GetUpdatesResponse.Response> responses = new ArrayList<>();
//        for (int i = 0; i < messagesAmount; i++) {
//            responses.add(new GetUpdatesResponse.Response(i,
//                    new GetUpdatesResponse.Message(i, null,
//                            new GetUpdatesResponse.Chat(i, "", "", "", ""), 0, "some response " + i)));
//
//        }
//
//        var response = new GetUpdatesResponse(true, responses);
//        when(client.getUpdates(any())).thenReturn(response);
//        when(generalMessageTextProcessor.process(any())).thenReturn(mock(Mono.class));
//
//        telegramService.getUpdates();
//
//        verify(generalMessageTextProcessor, atLeast(messagesAmount)).process(any());
//        verify(idKeeper, atLeast(1)).getUpdateId();
//        verify(idKeeper, atLeast(1)).setUpdateId(messagesAmount);
//    }
//
//
//
//    @Test
//    public void testProceedMessage() throws IOException, URISyntaxException {
//        URI path = ClassLoader.getSystemResource("provided.json").toURI();
//        var providedMessages = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
//        ObjectMapper mapper = new ObjectMapper();
//
//        System.out.println(providedMessages);
//    }
//
//}
