package org.openschool.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "example-service", url = "http://localhost:8080")
public interface ExampleFeignClient {

    @GetMapping("/example/hello")
    String getExample(@RequestHeader("example-header") String exampleHeader);

}
