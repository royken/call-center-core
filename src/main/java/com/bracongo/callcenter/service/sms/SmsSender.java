package com.bracongo.callcenter.service.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 *
 * @author vr.kenfack
 */
@Component
public class SmsSender {
    
    @Value("${sms.localapi}")
    private static String apiBaseUrl;
    
    private static RestTemplate getRestTemplate() {
        System.out.println("BASE URL = " + apiBaseUrl);
        DefaultUriBuilderFactory builderFactory = new DefaultUriBuilderFactory();
        builderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://bcgsrv16008.bracongo.local:8085/")
                .uriTemplateHandler(builderFactory)
                .build();
        return restTemplate;
    }
    
    public static SmsResponseDto create(UniqueDestinataireMsgDto msg) {
        String fooResourceUrl = "/smssender/sms/sendunique";
        HttpEntity<UniqueDestinataireMsgDto> request = new HttpEntity<>(msg);
        RestTemplate restTemplate = getRestTemplate();
        SmsResponseDto foo = restTemplate.postForObject(fooResourceUrl, request, SmsResponseDto.class);
        return foo;
    }
    
}
