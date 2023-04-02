package ca.sheridancollege.benerayj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ConnectionTestController {
    @GetMapping("/api/hello")
    public String greet() {
        return "Hi!";
    }
    
}
