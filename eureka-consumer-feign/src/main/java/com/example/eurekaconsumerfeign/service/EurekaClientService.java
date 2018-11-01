package com.example.eurekaconsumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(serviceId = "eureka-client")
public interface EurekaClientService {

    @GetMapping("/dc")
    String dc();
}
