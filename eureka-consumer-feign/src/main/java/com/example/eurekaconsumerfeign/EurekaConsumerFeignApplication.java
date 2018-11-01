package com.example.eurekaconsumerfeign;

import com.example.eurekaconsumerfeign.service.EurekaClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
@Slf4j
public class EurekaConsumerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeignApplication.class, args);
    }

    @Autowired
    private EurekaClientService eurekaClientService;

    @GetMapping("/dc")
    public String dc(){
        log.info(">>>>>eureka-consumer-feign.dc<<<<<");
        return eurekaClientService.dc();
    }

    @GetMapping("/jk/dc")
    public String jxdc(){
        log.info(">>>>>eureka-consumer-feign.jkdc<<<<<");
        return eurekaClientService.dc();
    }
}
