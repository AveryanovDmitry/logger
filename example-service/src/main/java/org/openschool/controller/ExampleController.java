package org.openschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@Schema(description = "Все операции функционала логирования.")
public class ExampleController {

    @GetMapping("/hello")
    @Operation(description = "Пример, для демонстрации логирования в консоль.")
    public String requestAndReceiveResponse() {
        return "Hello from example service";
    }

}
