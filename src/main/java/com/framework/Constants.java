package com.framework;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Constants {

    @NoArgsConstructor(access = PRIVATE)
    public static class Response {
        public static final String OUT_PUT_SIZE = "Compact";
        public static final String TIME_ZONE = "US/Eastern";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class ApiKey {
        public static final String API_KEY1 = "apikey1";
        public static final String API_KEY2 = "apikey2";
    }
}
