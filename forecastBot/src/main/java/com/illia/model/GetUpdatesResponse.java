package com.illia.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GetUpdatesResponse {

    boolean isStatusOk;
    List<Response> responses;


    @JsonCreator
    public GetUpdatesResponse(@JsonProperty("ok")boolean isStatusOk, @JsonProperty("result") List<Response> responses) {
        this.isStatusOk = isStatusOk;
        this.responses = responses;
    }

    @Value
    public static class Chat{
        long id;
        String firstName;
        String lastName;
        String userName;
        String type;

        @JsonCreator
        public Chat(@JsonProperty("id") long id,
                    @JsonProperty("first_name") String firstName,
                    @JsonProperty("last_name") String lastName,
                    @JsonProperty("username") String username,
                    @JsonProperty("type") String type) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = username;
            this.type = type;
        }
    }

    //sender
    @Value
    public static class From{

        long id;
        boolean isBot;
        String firstName;
        String lastName;
        String userName;
        String languageCode;
        @JsonCreator
        public From(@JsonProperty("id") long id,
                    @JsonProperty("is_bot") boolean isBot,
                    @JsonProperty("first_name") String firstName,
                    @JsonProperty("last_name") String lastName,
                    @JsonProperty("username") String userName,
                    @JsonProperty("language_code") String languageCode) {
            this.id = id;
            this.isBot = isBot;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.languageCode = languageCode;
        }

    }
    @Value
    public static class Message{

        long id;
        From from;
        Chat chat;
        long date;
        String text;
        @JsonCreator
        public Message(@JsonProperty("message_id") long id,
                       @JsonProperty("from") From from,
                       @JsonProperty("chat") Chat chat,
                       @JsonProperty("date") long date,
                       @JsonProperty("text") String text) {
            this.id = id;
            this.from = from;
            this.chat = chat;
            this.date = date;
            this.text = text;
        }


    }
    @Value
    @AllArgsConstructor
    public static class Response{
        long updateId;
        Message message;

        @JsonCreator
        public Response(@JsonProperty("update_id") long updateId, @JsonProperty("message") Message message, @JsonProperty("edited_message") Message editedMessage) {
            this.updateId = updateId;
            this.message = message != null ? message : editedMessage;
        }

    }

    @Override
    public String toString() {
        return "GetUpdatesResponse{" +
                "isStatusOk=" + isStatusOk +
                ", responses=" + responses +
                '}';
    }

}


















