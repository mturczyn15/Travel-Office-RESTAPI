package com.example.traveloffice.airlabs.config;

import lombok.Getter;

@Getter
public final class AirlabsConfigSingleton {

    private static AirlabsConfigSingleton airlabsConfigSingleton = null;
    private String airlabsApiEndpoint = "http://airlabs.co/api/v6";
    private String airlabsAppKey = "47202a42-0197-4e45-9ff0-4a2fae79558c";

    private AirlabsConfigSingleton() {

    }

    public static AirlabsConfigSingleton getInstance() {
        if (airlabsConfigSingleton == null) {
            synchronized(AirlabsConfigSingleton.class) {
                if (airlabsConfigSingleton == null) {
                    airlabsConfigSingleton = new AirlabsConfigSingleton();
                }
            }
        }
        return airlabsConfigSingleton;
    }
}
