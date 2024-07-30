package org.openschool.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClientRunner implements CommandLineRunner {

    private final ExampleFeignClient feignClient;

    @Override
    public void run(String... args) {
        String response = feignClient.getExample("example header");
        System.out.println("Response from Feign client: " + response);
    }
}
