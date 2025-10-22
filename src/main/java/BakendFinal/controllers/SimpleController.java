package BakendFinal.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class SimpleController {

    @GetMapping("/")
    public String home() {
        return "Backend funcionando! bld";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}