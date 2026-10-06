package com.micorservicesarch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

public class ClientConfig {
    @Configuration
    public static class RestTempletClient {
        @Bean
        public RestTemplate getRestTemplet(){
            return new RestTemplate();
        }

        @Bean
        public WebClient getWebClient(){
            return WebClient.builder().build();
        }

        @Bean
        public RestClient getRestClient(){
            return RestClient.builder().build();
        }
    }
}
