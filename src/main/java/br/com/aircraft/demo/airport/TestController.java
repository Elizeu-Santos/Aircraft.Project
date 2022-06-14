package br.com.aircraft.demo.airport;

import org.springframework.web.bind.annotation.GetMapping;

public class TestController {
    
    @GetMapping("/")
    public void getTest() {
        System.out.println("Hello Word!");
    }
}
