package com.illia.telegram.bot.service.processor.pattern;


import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum MessageTextPatterns {
    FULL_PROMPT_REQUEST_PATTERN(Pattern.compile("((-)?\\d+(\\.\\d+)?) ((-)?\\d+(\\.\\d+)?) (\\d)")),
    SHORT_PROMPT_REQUEST_PATTERN(Pattern.compile("(\\d)"));

    private final Pattern pattern;

    MessageTextPatterns(Pattern pattern) {
        this.pattern = pattern;
    }

}
