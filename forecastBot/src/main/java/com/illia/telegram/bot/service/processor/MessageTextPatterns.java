package com.illia.telegram.bot.service.processor;


import java.util.regex.Pattern;

/**
 * Has 2 valid variants:
 * Full | LAT LON NOD - returns forecast for provided location
 * Short | NOD - return forecast for Irpin, Kyiv Oblast', Ukraine (LAT = 50.523, LON = 30.24)
 * LAT - location latitude, decimal
 * LON - location longitude, decimal
 * NOD - number of days to output, integer
 */

public enum MessageTextPatterns {
    FULL(Pattern.compile("(\\d+(\\.\\d+)?) (\\d+(\\.\\d+)?) (\\d)")),
    SHORT(Pattern.compile("(\\d)"));

    private final Pattern pattern;

    MessageTextPatterns(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
